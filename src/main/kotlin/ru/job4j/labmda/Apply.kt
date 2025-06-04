package ru.job4j.labmda

fun main() {
    println(table(3, 4))
}

fun table(row: Int, cell: Int): String {
    val table = StringBuilder()
    table.apply {
        append("<table>\n")
        repeat(row) {
            append(" <tr>")
            repeat(cell) {
                append("<td></td>")
            }
            append("</tr>\n")
        }
        append("</table>")
    }
    return table.toString()
}