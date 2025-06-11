package ru.job4j.safe

class DirectoryOfCities {

    val directory by lazy { loadDirectory() }

    private fun loadDirectory(): List<String> = listOf("Москва", "Ленинград", "Саратов", "Ярославль")
}