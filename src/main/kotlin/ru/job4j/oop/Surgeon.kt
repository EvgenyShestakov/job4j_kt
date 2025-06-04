package ru.job4j.oop

class Surgeon(
    name: String,
    surname: String,
    qualification: String,
    val operationCount: Int,
) : Doctor(name, surname, qualification) {

    override fun getFullName(): String {
        println("Override from surgeon")
        return super.getFullName()
    }

    override fun action() {
        println("Execute from surgeon")
        super.action()
    }
}
