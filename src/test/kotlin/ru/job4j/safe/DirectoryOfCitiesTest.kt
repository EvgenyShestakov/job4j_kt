package ru.job4j.safe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DirectoryOfCitiesTest {

    @Test
    fun whenDirectoryLoad() {
        val directory = DirectoryOfCities()
        assertThat(directory.directory).containsExactly("Москва", "Ленинград", "Саратов", "Ярославль")
    }
}