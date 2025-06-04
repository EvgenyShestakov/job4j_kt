package ru.job4j.oop

class Cardiologist(
    name: String,
    surname: String,
    qualification: String,
    val yearsWithECG: Int,
) : Doctor(name, surname, qualification) {

    override fun getFullName(): String {
        println("Override from cardiologist")
        return super.getFullName()
    }

    override fun action() {
        println("Execute from cardiologist")
        super.action()
    }
}