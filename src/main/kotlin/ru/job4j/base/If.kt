package ru.job4j.base

fun max(first: Int, second: Int): Int =
    if (first > second) first else second

fun max(vararg numbers: Int): Int {
    var max = Int.MIN_VALUE
    for (number in numbers) {
        if (number > max) {
            max = number
        }
    }
    return max
}

fun main() {
    val maxOfTwoNumber = max(1, 2)
    println("max from 1 and 2 is $maxOfTwoNumber")
    val maxOfThreeNumber = max(3, 5, 7)
    println("max of 3, 5 and 7 is $maxOfThreeNumber")
}
