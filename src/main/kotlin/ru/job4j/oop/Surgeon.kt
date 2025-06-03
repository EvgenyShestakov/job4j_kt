package ru.job4j.oop

class Surgeon(
    name: String,
    surname: String,
    qualification: String,
    val operationCount: Int,
) : Doctor(name, surname, qualification)
