package ru.job4j.dsl

import org.apache.commons.dbcp2.BasicDataSource

class DataSourceDsl {

    internal val pool = BasicDataSource()

    fun driverClassName(driverClassName: String) =
        apply { pool.driverClassName = driverClassName }

    fun url(url: String) = apply { pool.url = url }

    fun userName(userName: String) = apply { pool.username = userName }

    fun password(password: String) = apply { pool.password = password }

    fun minIdle(minIdle: Int) = apply { pool.minIdle = minIdle }

    fun maxIdle(maxIdle: Int) = apply { pool.maxIdle = maxIdle }

    fun maxOpenPreparedStatements(maxOpenPreparedStatements: Int) =
        apply { pool.maxOpenPreparedStatements = maxOpenPreparedStatements }
}

fun dataSource(configure: DataSourceDsl.() -> Unit): BasicDataSource {
    val dsl = DataSourceDsl()
    dsl.configure()
    return dsl.pool
}


fun main() {
    val datasource = dataSource {
        driverClassName("org.postgres.Driver")
        url("localhost")
        userName("postgres")
        password("password")
        minIdle(5)
        maxIdle(10)
        maxOpenPreparedStatements(100)
    }

    println("driverClassName: ${datasource.driverClassName}")
    println("url: ${datasource.url}")
    println("userName: ${datasource.username}")
    println("password: ${datasource.password}")
    println("minIdle: ${datasource.minIdle}")
    println("maxIdle: ${datasource.maxIdle}")
    println("maxOpenPreparedStatements: ${datasource.maxOpenPreparedStatements}")
}

