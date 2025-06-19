package ru.job4j.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import kotlin.coroutines.cancellation.CancellationException

fun main() {
    val query = "replicaRequest"
    fun searchByName(query: String): String = "search by name: $query"
    fun searchByPhone(query: String): String = "search by phone: $query"
    fun searchByEmail(query: String): String = "search by email: $query"
    val searches: List<(String) -> String> = listOf(::searchByName, ::searchByPhone, ::searchByEmail)
    runBlocking {
        println("Run with while: ${searchWithWhile(query, searches)}")
        println("Run with select: ${searchWithSelect(query, searches)}")
    }
}

suspend fun searchWithWhile(
    query: String,
    searchers: List<(String) -> String>,
): String = coroutineScope {

    val deferreds: List<Deferred<String>> = launchSearchers(
        query,
        searchers
    )

    while (true) {
        deferreds.forEach { job ->
            if (job.isCompleted) {
                try {
                    val result = job.await()
                    if (result.isNotEmpty()) {
                        deferreds.forEach { if (!it.isCompleted) it.cancel() }
                        return@coroutineScope result
                    }
                } catch (ex: CancellationException) {
                    println("Coroutine wa cancelled ${ex.message}")
                }
            }
        }
        delay(10)
    }

    return@coroutineScope ""
}

suspend fun searchWithSelect(
    query: String,
    searchers: List<(String) -> String>,
): String = coroutineScope {

    val deferreds: List<Deferred<String>> = launchSearchers(
        query,
        searchers
    )

    return@coroutineScope select<String> {
        deferreds.forEach { job ->
            job.onAwait { answer ->
                if (answer.isNotEmpty()) {
                    deferreds.forEach { if (!it.isCompleted) it.cancel() }
                    answer
                } else {
                    ""
                }
            }
        }
    }
}

private fun <T> CoroutineScope.launchSearchers(
    query: String,
    searchers: List<(String) -> T>,
): List<Deferred<T>> = searchers.map { searchFunc ->
    async(Dispatchers.IO) {
        delay((200..500).random().toLong())
        searchFunc(query)
    }
}
