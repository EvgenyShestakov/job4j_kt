package ru.job4j.dsl

data class Sale(
    val productName: String,
    val category: String,
    val quantitySold: Int,
    val totalRevenue: Double,
)

data class SaleStatistics(
    val filteredByCategory: List<Sale>,
    val totalRevenue: Double,
    val topSellingProducts: List<Sale>,
    val filteredByMinRevenue: List<Sale>,
)

fun filterByCategory(sales: List<Sale>, category: String): List<Sale> {
    return sales.filter { it.category == category }
}

fun calculateTotalRevenue(sales: List<Sale>): Double {
    return sales.sumOf { it.totalRevenue }
}

fun topSellingProducts(sales: List<Sale>, topN: Int): List<Sale> {
    return sales.sortedBy { it.quantitySold }.take(topN)
}

fun filterByMinRevenue(sales: List<Sale>, minRevenue: Double): List<Sale> {
    return sales.filter { it.totalRevenue > minRevenue }
}

fun combinedSalesAnalysis(sales: List<Sale>, category: String, topN: Int): Double {
    return sales.asSequence()
        .filter { it.category == category }
        .sortedByDescending { it.totalRevenue }
        .take(topN)
        .sumOf { it.totalRevenue }
}

inline fun <T> analyzeSales(
    sales: List<Sale>,
    category: String,
    topN: Int,
    minRevenue: Double,
    block: (
        filterByCategory: List<Sale>,
        totalRevenue: Double,
        topSellingProducts: List<Sale>,
        filterByMinRevenue: List<Sale>,
    ) -> T,
): T {

    val filtered = filterByCategory(sales, category)
    val total = calculateTotalRevenue(sales)
    val top = topSellingProducts(sales, topN)
    val minRevenue = filterByMinRevenue(sales, minRevenue)
    return block(filtered, total, top, minRevenue)
}

fun main() {
    val sales = listOf(
        Sale("Laptop", "Electronics", 10, 5000.0),
        Sale("Smartphone", "Electronics", 20, 3000.0),
        Sale("Tablet", "Electronics", 15, 2000.0),
        Sale("Headphones", "Accessories", 50, 1500.0),
        Sale("Charger", "Accessories", 100, 1000.0)
    )

    val electronicsRevenue = combinedSalesAnalysis(sales, "Electronics", 3)
    println("Общая выручка от топ-2 продуктов категории 'Electronics': $electronicsRevenue")

    println()
    val analysis = analyzeSales(
        sales = sales,
        category = "Electronics",
        topN = 2,
        minRevenue = 1000.0
    ) { filterByCategory, total, top, filterByMinRevenue ->
        SaleStatistics(
            filteredByCategory = filterByCategory,
            totalRevenue = total,
            topSellingProducts = top,
            filteredByMinRevenue = filterByMinRevenue
        )
    }
    println("Фильтрация по категории ${analysis.filteredByCategory}")
    println("Расчет общей выручки ${analysis.totalRevenue}")
    println("Поиск самых продаваемых продуктов ${analysis.topSellingProducts}")
    println("Фильтрация по минимальной выручке ${analysis.filteredByMinRevenue}")
}
