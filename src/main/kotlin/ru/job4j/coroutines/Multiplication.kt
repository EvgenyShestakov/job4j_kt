package ru.job4j.coroutines

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

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
    answers: ReceiveChannel<String>,
): Int {
    val file = File(fileName)
    if (!file.exists()) error("Файл $fileName не найден")
    val questions: List<List<String>> = csvReader {
        delimiter = ';'
    }.readAll(file)

    var score = 0
    for (row in questions) {
        if (row.size < 2) continue
        println(row[0])

        val answer = answers.receiveCatching().getOrNull()
            ?: return score

        if (answer.trim() == row[1].trim()) {
            score++
            println("Правильный ответ")
        } else {
            println("Неправильный ответ")
        }
    }
    return score
}