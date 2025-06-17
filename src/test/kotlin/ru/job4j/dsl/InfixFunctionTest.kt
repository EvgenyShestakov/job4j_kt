package ru.job4j.dsl

import org.junit.jupiter.api.Test

class InfixFunctionTest {

    @Test
    fun whenStringsEqPassesThenStringsAreEqual() {
        val first = "kotlin"
        val second = "kotlin"
        first eq second
    }

    @Test
    fun whenStringsNotEqPassesThenStringsAreNotEqual() {
        val first = "kotlin"
        val second = "java"
        first notEq second
    }

    @Test
    fun whenListContainsElemOtherList() {
        listOf("kotlin", "java", "rust") contains listOf("kotlin", "java", "rust")
    }
}