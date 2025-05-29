package ru.job4j.base

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalcTest {

    @Test
    fun when2Plus2() {
        assertThat(add(2, 2)).isEqualTo(4)
    }

    @Test
    fun when2Minus2() {
        assertThat(subtract(2, 2)).isEqualTo(0)
    }

    @Test
    fun when2Multiply2() {
        assertThat(multi(2, 2)).isEqualTo(4)
    }

    @Test
    fun when2Divine2() {
        assertThat(dev(2, 2)).isEqualTo(1)
    }
}
