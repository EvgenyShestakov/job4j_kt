package ru.job4j.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        simulateLoading()
    }
    println("Привет из основного потока!")
}

suspend fun simulateLoading() {
    for (i in 0..100) {
        delay(10L)
        println("$i%")
    }
    println("Загрузка завершена")
}