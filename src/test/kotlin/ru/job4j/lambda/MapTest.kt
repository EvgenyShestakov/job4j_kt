package ru.job4j.lambda

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import ru.job4j.labmda.Address
import ru.job4j.labmda.Company
import java.time.LocalDate

class MapTest {

    @Test
    fun whenCompaniesMapping() {
        val expectedResult = listOf(
            """Company name Apple.
       | Address: USA, Cupertino, Apple Park Way,
       |  1.
       |   Create by 1976-04-01""".trimMargin(),
            """Company name Google.
       | Address: USA, Mountain View, Amphitheatre Parkway,
       |  1600.
       |   Create by 1998-09-04""".trimMargin(),
            """Company name Amazon.
       | Address: USA, Seattle, Leonard St,
       |  410 Terry Ave N.
       |   Create by 1994-07-05""".trimMargin()
        )
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
        assertThat(companyViews).isEqualTo(expectedResult)
    }
}