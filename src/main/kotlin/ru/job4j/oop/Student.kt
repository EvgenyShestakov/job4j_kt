package ru.job4j.oop

data class Student(var name: String = "", var surname: String = "", var phone: String = "", val email: String = "") {

    constructor(value: String, type: StudentField) : this(
        name = if (type == StudentField.NAME) value else "",
        surname = if (type == StudentField.SURNAME) value else "",
        phone = if (type == StudentField.PHONE) value else "",
        email = if (type == StudentField.EMAIL) value else ""
    )
}






