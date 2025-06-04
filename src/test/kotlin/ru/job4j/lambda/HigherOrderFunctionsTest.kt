package ru.job4j.lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import ru.job4j.labmda.operation

class HigherOrderFunctionsTest {

    @Test
    fun whenOperationAdd() {
        val add = operation("add")
        assertThat(add(5.0, 5.0)).isEqualTo(10.0)
    }

    @Test
    fun whenOperationSubtract() {
        val subtract = operation("subtract")
        println(subtract(17.0, 5.0))
        assertThat(subtract(17.0, 5.0)).isEqualTo(12.0)
    }

    @Test
    fun whenOperationMultiply() {
        val multiply = operation("multiply")
        println(multiply(5.0, 7.0))
        assertThat(multiply(5.0, 7.0)).isEqualTo(35.0)
    }

    @Test
    fun whenOperationDivide() {
        val divide = operation("divide")
        println(divide(100.0, 4.0))
        assertThat(divide(100.0, 4.0)).isEqualTo(25.0)
    }

    @Test
    fun whenOperationMax() {
        val max = operation("max")
        println(max(10.0, 8.0))
        assertThat(max(10.0, 8.0)).isEqualTo(10.0)
    }

    @Test
    fun whenOperationMin() {
        val min = operation("min")
        println(min(3.0, 6.0))
        assertThat(min(3.0, 6.0)).isEqualTo(3.0)
    }

    @Test
    fun whenOperationAvg() {
        val avg = operation("avg")
        println(avg(3.0, 9.0))
        assertThat(avg(3.0, 9.0)).isEqualTo(6.0)
    }
}