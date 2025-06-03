package ru.job4j.oop

class Cardiologist(
    name: String,
    surname: String,
    qualification: String,
    val yearsWithECG: Int,
) : Doctor(name, surname, qualification)