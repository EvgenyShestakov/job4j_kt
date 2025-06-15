package ru.job4j.dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InlineTest {

    private val sales = listOf(
        Sale("Laptop", "Electronics", 10, 5000.0),
        Sale("Smartphone", "Electronics", 20, 3000.0),
        Sale("Tablet", "Electronics", 15, 2000.0),
        Sale("Headphones", "Accessories", 50, 1500.0),
        Sale("Charger", "Accessories", 100, 1000.0)
    )

    @Test
    fun whenFilterByCategoryElectronicsThen3Sales() {
        val result = filterByCategory(sales, "Electronics")
        assertThat(result).hasSize(3)
        assertThat(result.map { it.productName }).containsExactlyInAnyOrder(
            "Laptop", "Smartphone", "Tablet"
        )
    }

    @Test
    fun whenCalculateTotalRevenueThenSumAll() {
        val expected = sales.sumOf { it.totalRevenue }
        val result = calculateTotalRevenue(sales)
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun whenTopSellingProductsTop2ThenMinQuantity() {
        val expected = sales.sortedBy { it.quantitySold }.take(2).map { it.productName }
        val result = topSellingProducts(sales, 2).map { it.productName }
        assertThat(result).containsExactlyElementsOf(expected)
    }

    @Test
    fun whenFilterByMinRevenue2000Then3Sales() {
        val expected = sales.filter { it.totalRevenue > 2000.0 }.map { it.productName }
        val result = filterByMinRevenue(sales, 2000.0).map { it.productName }
        assertThat(result).containsExactlyInAnyOrderElementsOf(expected)
    }

    @Test
    fun whenAnalyzeSalesAllFunctionsWork() {
        val stats = analyzeSales(
            sales = sales,
            category = "Electronics",
            topN = 2,
            minRevenue = 1000.0
        ) { filteredByCategory, totalRevenue, topSellingProducts, filteredByMinRevenue ->
            SaleStatistics(
                filteredByCategory = filteredByCategory,
                totalRevenue = totalRevenue,
                topSellingProducts = topSellingProducts,
                filteredByMinRevenue = filteredByMinRevenue
            )
        }
        val expectedFiltered = sales.filter { it.category == "Electronics" }.map { it.productName }
        val expectedTotal = sales.sumOf { it.totalRevenue }
        val expectedTop = sales.sortedBy { it.quantitySold }.take(2).map { it.productName }
        val expectedMinRevenue = sales.filter { it.totalRevenue > 1000.0 }.map { it.productName }

        assertThat(stats.filteredByCategory.map { it.productName })
            .containsExactlyInAnyOrderElementsOf(expectedFiltered)
        assertThat(stats.totalRevenue).isEqualTo(expectedTotal)
        assertThat(stats.topSellingProducts.map { it.productName })
            .containsExactlyElementsOf(expectedTop)
        assertThat(stats.filteredByMinRevenue.map { it.productName })
            .containsExactlyInAnyOrderElementsOf(expectedMinRevenue)
    }

    @Test
    fun whenCombinedSalesAnalysisElectronicsTop2ThenSumOfTop2ByRevenue() {
        val expected = sales
            .asSequence()
            .filter { it.category == "Electronics" }
            .sortedByDescending { it.totalRevenue }
            .take(2)
            .sumOf { it.totalRevenue }
        val result = combinedSalesAnalysis(sales, "Electronics", 2)
        assertThat(result).isEqualTo(expected)
    }
}