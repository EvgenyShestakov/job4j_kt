package ru.job4j.condition

class ConsoleInput(val output: Output) : Input {
    override fun ascString(question: String): String {
        println(question)
        return readln()
    }

    override fun ascInt(question: String): Int? {
        return ascString(question).toIntOrNull()
    }
}