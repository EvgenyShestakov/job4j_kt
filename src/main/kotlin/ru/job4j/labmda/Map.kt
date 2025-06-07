package ru.job4j.labmda

import java.time.LocalDate

fun main() {
    val companies = listOf(
        Company(
            name = "Apple",
            address = Address(
                country = "USA",
                city = "Cupertino",
                street = "Apple Park Way",
                building = "1"
            ),
            created = LocalDate.of(1976, 4, 1)
        ),
        Company(
            name = "Google",
            address = Address(
                country = "USA",
                city = "Mountain View",
                street = "Amphitheatre Parkway",
                building = "1600"
            ),
            created = LocalDate.of(1998, 9, 4)
        ),
        Company(
            name = "Amazon",
            address = Address(
                country = "USA",
                city = "Seattle",
                street = "Leonard St",
                building = "410 Terry Ave N"
            ),
            created = LocalDate.of(1994, 7, 5)
        )
    )
    val mapCompany: (Company) -> String = {"""Company name ${it.name}.
        | Address: ${it.address.country}, ${it.address.city}, ${it.address.street},
        |  ${it.address.building}.
        |   Create by ${it.created}""".trimMargin() }
    val companyViews = companies.map(mapCompany)
    companyViews.forEach{println(it)}
}

data class Company(val name: String, val address: Address, val created: LocalDate)

data class Address(val country: String, val city: String, val street: String, val building: String)