package ru.job4j.oop

open class Doctor(
    val name: String,
    val surname: String,
    val qualification: String,
) {
    open fun getFullName(): String {
        println("Execute doctor getFullName()")
        return "$surname $name"
    }

    open fun action() = println("Execute doctor action")
}
