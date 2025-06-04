package ru.job4j.labmda

import kotlin.math.pow

fun main() {
    var dec = { x: Int -> (x - 1).toDouble().pow(2).toInt() }
    println(dec(5))
}

