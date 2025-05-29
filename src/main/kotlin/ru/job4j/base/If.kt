package ru.job4j.base

fun max(first: Int, second: Int): Int = if (first > second) first else second

fun max(first: Int, second: Int, third: Int): Int = if (max(first, second) > third) max(first, second) else third

fun main() {
    val maxOfTwoNumber = max(1, 2)
    println("max from 1 and 2 is $maxOfTwoNumber")
    val maxOfThreeNumber = max(3, 5, 7)
    println("max of 3, 5 and 7 is $maxOfThreeNumber")
}
