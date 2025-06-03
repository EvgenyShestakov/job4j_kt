package ru.job4j.oop

class Neurologist(
    name: String,
    surname: String,
    qualification: String,
    val mainDisorder: String,
) : Doctor(name, surname, qualification)
