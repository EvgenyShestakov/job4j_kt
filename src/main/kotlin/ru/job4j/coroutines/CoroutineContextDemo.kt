package ru.job4j.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

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
    val channel = Channel<String>()
    val jobs = searchers.map { searchFunction ->
        launch(Dispatchers.IO) {
            val rsl = searchFunction(query)
            if (rsl.isNotEmpty()) {
                channel.send(rsl)
            }
        }
    }

    val result = withTimeoutOrNull(2000) {
        channel.receive()
    } ?: ""
    jobs.forEach { it.cancel() }
    channel.close()
    result
}

suspend fun fast(query: String): String {
    delay(100)
    return "search by fast: $query"
}

suspend fun slow(query: String): String {
    delay(1000)
    return "search by slow: $query"
}

