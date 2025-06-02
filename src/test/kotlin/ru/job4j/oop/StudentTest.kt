package ru.job4j.oop

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StudentTest {

    @Test
    fun whenCreateStudent() {
        val vasia = Student(
            name = "Вася",
            surname = "Пупкин",
            phone = "7512255",
        )
        val tolia = Student(
            name = "Толя",
            surname = "Канарейкин",
            phone = "4568244",
            email = "tolian7384@mail.ru",
        )

        assertThat(vasia)
            .hasFieldOrPropertyWithValue("name", "Вася")
            .hasFieldOrPropertyWithValue("surname", "Пупкин")
            .hasFieldOrPropertyWithValue("phone", "7512255")
            .hasFieldOrPropertyWithValue("email", "")

        assertThat(tolia)
            .hasFieldOrPropertyWithValue("name", "Толя")
            .hasFieldOrPropertyWithValue("surname", "Канарейкин")
            .hasFieldOrPropertyWithValue("phone", "4568244")
            .hasFieldOrPropertyWithValue("email", "tolian7384@mail.ru")
    }
}