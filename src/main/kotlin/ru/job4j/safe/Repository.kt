package ru.job4j.safe

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.Properties

class Repository {
    private lateinit var connection: Connection

    fun init(cfg: Properties) {
        val loader = Repository::class.java.classLoader
        loader.getResourceAsStream("db.properties").use { io ->
            if (io != null) {
                cfg.load(io)
            } else {
                error("Файл db.properties не найден!")
            }
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"))
            val url = cfg.getProperty("jdbc.url")
            val login = cfg.getProperty("jdbc.login")
            val password = cfg.getProperty("jdbc.password")
            connection = DriverManager.getConnection(url, login, password)
        } catch (e: ClassNotFoundException) {
            throw IllegalStateException(e)
        } catch (e: SQLException) {
            throw IllegalStateException(e)
        }
    }

    fun exec(sql: String): String {
        try {
            connection.createStatement().use { stmt ->
                stmt.execute(sql)
                return "Выполнено: $sql"
            }
        } catch (e: SQLException) {
            throw IllegalStateException(e)
        }
    }
}