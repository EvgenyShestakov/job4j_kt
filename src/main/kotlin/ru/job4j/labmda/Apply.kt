package ru.job4j.labmda

fun main() {
    println(buildTable(3, 4))
}

fun buildTable(row: Int, cell: Int) = buildString {
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
