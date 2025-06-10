package ru.job4j.safe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PromotionTest {

    @Test
    fun whenPromotionNotEquals() {
        val blackFridayPromo = Promotion("Black Friday", "US", LocalDate.of(2025, 1, 24))
        val sale50Promo = Promotion("50% Off Everything", "US", LocalDate.of(2025, 4, 10))
        assertThat(blackFridayPromo).isNotEqualTo(sale50Promo)
    }

    @Test
    fun whenPromotionEquals() {
        val blackFridayPromo = Promotion("Black Friday", "US", LocalDate.of(2025, 1, 24))
        val blackFridayPromo2 = Promotion("Black Friday", "US", LocalDate.of(2025, 1, 24))
        assertThat(blackFridayPromo).isEqualTo(blackFridayPromo2)
    }
}