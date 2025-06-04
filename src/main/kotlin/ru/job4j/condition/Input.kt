package ru.job4j.condition

interface Input {
    fun ascString(question: String): String

    fun ascInt(question: String): Int?
}