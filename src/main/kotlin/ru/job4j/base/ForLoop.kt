package ru.job4j.base

fun main() {
    draw(5)
}

fun draw(size: Int) {
    var start = size
    var finish = -size
    for (y in start downTo finish) {
        for (x in start downTo finish) {
            if (Math.abs(y) == Math.abs(x)) {
                print("X")
            } else {
                print(" ")
            }
        }
        println()
    }
}
