package ru.job4j.coroutines

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DataService {

    suspend fun fetchDataFromServer(id: Int): String {
        println("Fetching data for $id")
        delay(1000)
        println("Data from server $id fetched")
        return "Data from server $id"
    }

    suspend fun saveDataToDatabase(data: String) {
        println("Saving $data")
        delay(1000)
        println("$data saved")
    }
}

fun main() {
    println("Start processing")

    val service = DataService()
    val jobs = mutableListOf<Job>()

    runBlocking {
        repeat(10) { id ->
            val job = launch {
                val data = service.fetchDataFromServer(id)
                service.saveDataToDatabase(data)
            }
            jobs += job
        }
        jobs.forEach { it.join() }
    }

    println("Processing completed.")
}
