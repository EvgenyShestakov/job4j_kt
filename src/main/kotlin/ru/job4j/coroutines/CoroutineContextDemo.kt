package ru.job4j.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicReference

fun main() {
    runBlocking {
        val searches: List<suspend (String) -> String> = listOf(::slow, ::fast)
        val result = search("request", searches)
        println("Result: $result")
    }
}

suspend fun search(
    query: String,
    searchers: List<suspend (String) -> String>,
): String = coroutineScope {
    val atomic = AtomicReference<String>()
    val latch = CountDownLatch(1)
    val jobs = searchers.map { searchFunction ->
        launch(Dispatchers.IO) {
            val rsl = searchFunction(query)
            if (rsl.isNotEmpty() && latch.count > 0) {
                atomic.set(rsl)
                latch.countDown()
            }
        }
    }
    withTimeoutOrNull(2000) {
        withContext(Dispatchers.IO) {
            latch.await()
        }
    }

    jobs.forEach { it.cancel() }
    atomic.get() ?: ""
}

suspend fun fast(query: String): String {
    delay(100)
    return "search by fast: $query"
}

suspend fun slow(query: String): String {
    delay(1000)
    return "search by slow: $query"
}

