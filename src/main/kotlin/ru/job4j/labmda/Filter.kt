package ru.job4j.labmda

fun main() {
    val accounts = listOf(
        Account("Nikolay", 452.25),
        Account("Ivan", 856.32),
        Account("Ivan", -254.32),
        Account("Dmitry", 356.12)
    )
    val filterUser: (Account) -> Boolean = { it.name == "Ivan" && it.balance > 0 }
    println(accounts.filter(filterUser))
}

data class Account(val name: String, val balance: Double)