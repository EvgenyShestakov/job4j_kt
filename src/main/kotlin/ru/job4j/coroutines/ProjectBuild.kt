package ru.job4j.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class Event(val id: Int)

class ProjectBuild(
    internal val eventChannel: Channel<Event> = Channel<Event>(),
    private val scope: CoroutineScope,
) {

    suspend fun addEvent(event: Event) {
        eventChannel.send(event)
    }

    fun start(): List<Job> {
        val processors = Runtime.getRuntime().availableProcessors()
        val jobs = mutableListOf<Job>()

        repeat(processors) {
            val job = scope.launch {
                for (event in eventChannel) {
                    println("Начало обработки события: $event")
                    delay(1000L)
                    println("Событие обработано: $event")
                }
            }
            jobs += job
        }
        return jobs
    }
}

fun main() {
    runBlocking {
        with(ProjectBuild(scope = this)) {
            val jobs = start()
            launch {
                for (i in 1..10) {
                    addEvent(Event(id = i))
                }
                eventChannel.close()
            }
            jobs.joinAll()
        }
    }
}