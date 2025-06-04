package ru.job4j.condition

interface UserAction {
    fun execute(tracker: Tracker, input: Input): Boolean

    fun getName(): String
}