package ru.job4j.labmda

fun main() {
    val add = operation("add")
    println(add(5.0, 5.0))

    val subtract = operation("subtract")
    println(subtract(17.0, 5.0))

    val multiply = operation("multiply")
    println(multiply(5.0, 7.0))

    val divide = operation("divide")
    println(divide(100.0, 4.0))

    val max = operation("max")
    println(max(10.0, 8.0))

    val min = operation("min")
    println(min(3.0, 6.0))

    val avg = operation("avg")
    println(avg(3.0, 9.0))

}

fun operation(operation: String): (Double, Double) -> Double {
    return when (operation) {
        "add" -> { a, b -> a + b }
        "subtract" -> { a, b -> a - b }
        "multiply" -> { a, b -> a * b }
        "divide" -> { a, b -> a / b }
        "max" -> { a, b -> a.coerceAtLeast(b) }
        "min" -> { a, b -> a.coerceAtMost(b) }
        "avg" -> { a, b -> (a + b) / 2 }
        else -> { a, b -> Double.NaN }
    }
}
