package ru.job4j.lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import ru.job4j.labmda.count
import ru.job4j.labmda.sum

class SequenceTest {

    @Test
    fun whenNumberSum() {
        val listNumbers = listOf(1, 2, 3, 4, 5)
        assertThat(sum(listNumbers)).isEqualTo(8)
    }

    @Test
    fun whenStringCount() {
        val listStrings = listOf("Winter", "Summer", "Spring", "Autumn")
        assertThat(count(listStrings)).isEqualTo(2)
    }
}