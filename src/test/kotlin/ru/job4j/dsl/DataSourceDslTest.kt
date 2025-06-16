package ru.job4j.dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DataSourceDslTest {

    @Test
    fun whenConfigureDatasourceWithDslSetsAllFieldsCorrectly() {
        val datasource = dataSource {
            driverClassName("org.postgres.Driver")
            url("localhost")
            userName("postgres")
            password("password")
            minIdle(5)
            maxIdle(10)
            maxOpenPreparedStatements(100)
        }

        assertThat(datasource.driverClassName).isEqualTo("org.postgres.Driver")
        assertThat(datasource.url).isEqualTo("localhost")
        assertThat(datasource.username).isEqualTo("postgres")
        assertThat(datasource.password).isEqualTo("password")
        assertThat(datasource.minIdle).isEqualTo(5)
        assertThat(datasource.maxIdle).isEqualTo(10)
        assertThat(datasource.maxOpenPreparedStatements).isEqualTo(100)
    }

    @Test
    fun whenConfigureOnlySomeFieldsLeavesOthersWithDefaults() {
        val datasource = dataSource {
            driverClassName("org.h2.Driver")
            url("jdbc:h2:mem:test")
        }

        assertThat(datasource.driverClassName).isEqualTo("org.h2.Driver")
        assertThat(datasource.url).isEqualTo("jdbc:h2:mem:test")
        assertThat(datasource.username).isNull()
        assertThat(datasource.password).isNull()
        assertThat(datasource.minIdle).isEqualTo(0)
        assertThat(datasource.maxIdle).isEqualTo(8)
        assertThat(datasource.maxOpenPreparedStatements).isEqualTo(-1)
    }
}