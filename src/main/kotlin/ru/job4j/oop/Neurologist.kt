package ru.job4j.oop

class Neurologist(
    name: String,
    surname: String,
    qualification: String,
    val mainDisorder: String,
) : Doctor(name, surname, qualification) {

    override fun getFullName(): String {
        println("Override from neurologist")
        return super.getFullName()
    }

    override fun action() {
        println("Execute from neurologist")
        super.action()
    }
}
