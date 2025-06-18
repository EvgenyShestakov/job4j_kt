package ru.job4j.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job1 = launch(Dispatchers.IO) {
        println("Запуск первой корутины с Dispatchers.IO")
        delay(2000L)
        println("Завершение работы первой корутины")
    }

    val job2 = launch(Dispatchers.Default) {
        println("Запуск второй корутины с Dispatchers.Default")
        delay(3000)
        println("Завершение работы второй корутины")
    }

    val job3 = launch(Dispatchers.Unconfined) {
        println("Запуск третьей корутины с Dispatchers.Unconfined")
        delay(4000)
        println("Завершение работы третьей корутины")
    }

    job1.join()
    job2.join()
    job3.join()

    println("Все задачи завершены")
}