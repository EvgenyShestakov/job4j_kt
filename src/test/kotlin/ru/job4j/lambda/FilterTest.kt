package ru.job4j.lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import ru.job4j.labmda.Account

class FilterTest {

    @Test
    fun whenAccountFiltered() {
        val accounts = listOf(
            Account("Nikolay", 452.25),
            Account("Ivan", 856.32),
            Account("Ivan", -254.32),
            Account("Dmitry", 356.12)
        )
        val filtered = accounts.filter { it.name == "Ivan" && it.balance > 0 }
        assertThat(filtered.size).isEqualTo(1)
        assertThat(filtered).containsExactly(Account("Ivan", 856.32))
    }
}