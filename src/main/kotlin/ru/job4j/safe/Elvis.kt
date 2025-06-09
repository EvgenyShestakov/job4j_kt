package ru.job4j.safe

import java.time.LocalDateTime

fun main() {
    val purchases = listOf(
        Purchase(
            name = "Lamp",
            created = LocalDateTime.of(2023, 10, 15, 13, 56, 11),
            address = Address(
                street = "Walker",
                home = "22",
                zip = Zip(15623)
            )
        ),
        Purchase(
            name = "Table",
            created = LocalDateTime.of(2023, 11, 5, 9, 34, 50),
            address = Address(
                street = "Baker",
                home = "10A",
                zip = null
            )
        ),
        Purchase(
            name = "Notebook",
            created = LocalDateTime.of(2023, 9, 25, 8, 5, 0),
            address = null
        ),
        Purchase(
            name = "Chair",
            created = LocalDateTime.of(2023, 12, 1, 16, 22, 7),
            address = Address(
                street = "Main",
                home = "7",
                zip = Zip(null)
            )
        )
    )
    println(purchasesToHtmlTable(purchases))
}

fun purchasesToHtmlTable(purchases: List<Purchase>): String {
    val header = "<tr><th>Имя</th><th>Дата</th><th>Адрес</th></tr>"
    val rows = purchases.joinToString("\n") { purchase ->
        val address = purchase.address?.let { addr ->
            buildList {
                add(addr.street)
                add(addr.home)
                addr.zip?.code?.let { code ->
                    add(code.toString())
                }
            }.joinToString(", ")
        } ?: ""
        "<tr><td>${purchase.name}</td><td>${purchase.created}</td><td>$address</td></tr>"
    }
    return "<table>\n$header\n$rows\n</table>"
}

data class Purchase(val name: String, val created: LocalDateTime, var address: Address?)

data class Address(val street: String, val home: String, var zip: Zip?)

data class Zip(val code: Int?)