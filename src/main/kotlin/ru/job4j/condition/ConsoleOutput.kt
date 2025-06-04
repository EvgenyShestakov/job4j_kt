package ru.job4j.condition

class ConsoleOutput : Output {
    override fun println(any: Any) {
        kotlin.io.println(any)
    }
}