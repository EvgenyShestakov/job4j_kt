package ru.job4j.safe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PromotionTest {

    @Test
    fun whenPromotionNotEquals() {
        val blackFridayPromo = Promotion(
            name = "Black Friday",
            currency = "US",
            date = LocalDate.of(2025, 1, 24)
        )
        val sale50Promo = Promotion(
            name = "50% Off Everything",
            currency = "US",
            date = LocalDate.of(2025, 4, 10)
        )
        assertThat(blackFridayPromo).isNotEqualTo(sale50Promo)
    }

    @Test
    fun whenPromotionEquals() {
        val blackFridayPromo = Promotion(
            name = "Black Friday",
            currency = "US",
            date = LocalDate.of(2025, 1, 24)
        )
        val blackFridayPromo2 = Promotion(
            name = "Black Friday",
            currency = "US",
            date = LocalDate.of(2025, 1, 24)
        )
        assertThat(blackFridayPromo).isEqualTo(blackFridayPromo2)
    }
}