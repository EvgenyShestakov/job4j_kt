package ru.job4j.base

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ArrayTest {

    @Test
    fun whenNonNullValuesAreMovedToArrayBeginning() {
        val actualArray = arrayOfNulls<String>(10)
        actualArray[0] = "Oleg"
        actualArray[3] = "Ivan"
        actualArray[6] = "Anton"
        actualArray[8] = "Nikita"
        val expectedArray = arrayOfNulls<String>(10)
        expectedArray[0] = "Oleg"
        expectedArray[1] = "Ivan"
        expectedArray[2] = "Anton"
        expectedArray[3] = "Nikita"
        assertThat(defragment(actualArray)).containsExactly(*expectedArray)
    }
}