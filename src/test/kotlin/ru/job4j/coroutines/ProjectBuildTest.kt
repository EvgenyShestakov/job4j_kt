package ru.job4j.coroutines

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProjectBuildTest {

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    @Test
    fun whenDataExchangeBetweenCoroutineSuccess() {
        runTest() {
            with(ProjectBuild(scope = this)) {
                val jobs = start()
                launch {
                    for (i in 1..10) {
                        addEvent(Event(id = i))
                    }
                    eventChannel.close()
                }
                jobs.joinAll()

                assertThat(eventChannel.isClosedForReceive).isTrue()
                jobs.forEach { assertThat(it.isCompleted).isTrue() }
                assertThat(jobs.size).isEqualTo(Runtime.getRuntime().availableProcessors())
            }
        }
    }
}