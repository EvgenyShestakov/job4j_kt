package ru.job4j.safe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ElvisTest {

    @Test
    fun whenFormatPurchasesAsHTMLTable() {
        val purchases = listOf(
            Purchase(
                name = "Lamp",
                created = LocalDateTime.of(2023, 10, 15, 13, 56, 11),
                address = Address("Walker", "22", Zip(15623))
            ),
            Purchase(
                name = "Table",
                created = LocalDateTime.of(2023, 11, 5, 9, 34, 50),
                address = Address("Baker", "10A", null)
            ),
            Purchase(
                name = "Notebook",
                created = LocalDateTime.of(2023, 9, 25, 8, 5, 0),
                address = null
            ),
            Purchase(
                name = "Chair",
                created = LocalDateTime.of(2023, 12, 1, 16, 22, 7),
                address = Address("Main", "7", Zip(null))
            )
        )

        val expected = """
            <table>
            <tr><th>Имя</th><th>Дата</th><th>Адрес</th></tr>
            <tr><td>Lamp</td><td>2023-10-15T13:56:11</td><td>Walker, 22, 15623</td></tr>
            <tr><td>Table</td><td>2023-11-05T09:34:50</td><td>Baker, 10A</td></tr>
            <tr><td>Notebook</td><td>2023-09-25T08:05</td><td></td></tr>
            <tr><td>Chair</td><td>2023-12-01T16:22:07</td><td>Main, 7</td></tr>
            </table>
        """.trimIndent()

        val actual = purchasesToHtmlTable(purchases)

        assertThat(actual).isEqualTo(expected)
    }
}
