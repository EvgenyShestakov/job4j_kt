package ru.job4j.base

fun main() {
    val plus = add(2, 2)
    println("2 + 2 = $plus")
    val minus = subtract(1, 1)
    println("2 - 2 = $minus")
    val multiply = multi(2, 2)
    println("2 * 2 = $multiply")
    val divide = dev(2, 2)
    println("2 / 2 = $divide")
}

fun add(a: Int, b: Int): Int {
    return a + b
}

fun subtract(a: Int, b: Int): Int {
    return a - b
}

fun multi(a: Int, b: Int): Int {
    return a * b
}

fun dev(a: Int, b: Int): Int {
    return a / b
}
