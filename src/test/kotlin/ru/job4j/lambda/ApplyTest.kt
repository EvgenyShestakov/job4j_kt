package ru.job4j.lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import ru.job4j.labmda.buildTable

class ApplyTest {

    @Test
    fun whenTableCrested() {
        val expectedTable = """
        <table>
         <tr><td></td><td></td><td></td><td></td></tr>
         <tr><td></td><td></td><td></td><td></td></tr>
         <tr><td></td><td></td><td></td><td></td></tr>
        </table>
        """.trimIndent()
        assertThat(buildTable(3, 4).trimIndent()).isEqualTo(expectedTable)
    }
}