package ru.job4j.base

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IfTest {

    @Test
    fun whenMaxOfTwo() {
        assertThat(max(4, 9)).isEqualTo(9)
    }

    @Test
    fun whenMaxOfThree() {
        assertThat(max(12, 38, 4)).isEqualTo(38)
    }
}