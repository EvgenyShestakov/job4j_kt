package ru.job4j.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val answers = Channel<String>(Channel.UNLIMITED)

        val readerJob = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                readlnOrNull()?.let { answers.trySend(it) } ?: break
            }
        }

        val scoreJob = async { survey("multiplication.csv", answers) }

        launch {
            delay(30_000)
            println("Время опроса закончилось")
            answers.close()
        }

        println("Ваш результат: ${scoreJob.await()}")

        readerJob.cancel()
    }
}

suspend fun survey(
    fileName: String,
    answers: ReceiveChannel<String>
): Int {
    val stream = object {}.javaClass.classLoader
        .getResourceAsStream(fileName) ?: error("Файл $fileName не найден")

    var score = 0
    stream.bufferedReader().useLines { lines ->
        for (line in lines) {
            val (question, right) = line.split(';')
            println(question)

            val answer = answers.receiveCatching().getOrNull()
                ?: return score

            if (answer.trim() == right.trim()) {
                score++
                println("Правильный ответ")
            } else {
                println("Неправильный ответ")
            }
        }
    }
    return score
}