package ru.job4j.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException

class OperationService {

    suspend fun performOperation(timeout: Long) {
        try {
            for (i in 1..5) {
                println("Job: I'm working on $i")
                delay(timeout)
            }
            println("Job has been successfully completed.")
        } catch (e: CancellationException) {
            println("Job has been canceled. Message ${e.message}.")
        }
    }
}

fun main() = runBlocking {
    val dataService = OperationService()
    val job = launch {
        dataService.performOperation(500)
    }
    launch {
        delay(2000)
        job.cancel()
    }
    job.join()
}
