package ru.job4j.labmda

fun main() {
val listNumbers = listOf(1, 2, 3, 4, 5)
    println(sum(listNumbers))
val listStrings = listOf("Winter", "Summer", "Spring", "Autumn")
    println(count(listStrings))
}

fun sum(list: List<Int>): Int {
    return list.asSequence()
        .filter { it % 2 == 0 }
        .map { it + 1 }
        .sum()
}

fun count(list: List<String>): Int {
    return list.asSequence()
        .filter { it.startsWith("S") }
        .count()
}