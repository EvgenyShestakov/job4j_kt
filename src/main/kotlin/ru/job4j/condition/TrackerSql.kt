package ru.job4j.condition

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.Statement.RETURN_GENERATED_KEYS
import java.util.Properties

class TrackerSql(
    resourceName: String = "db.properties",
) : Store<Item>, AutoCloseable {

    internal val connection: Connection

    init {
        val loader = TrackerSql::class.java.classLoader
        loader.getResourceAsStream(resourceName)?.use { input ->
            val config = Properties().apply { load(input) }
            Class.forName(config.getProperty("jdbc.driver"))
            connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.login"),
                config.getProperty("jdbc.password")
            )
        } ?: error("Файл $resourceName не найден!")
    }

    inline fun <T> Connection.withStatement(
        sql: String,
        generatedKeys: Boolean = false,
        block: PreparedStatement.() -> T,
    ): T {
        val stmt = if (generatedKeys)
            prepareStatement(sql, RETURN_GENERATED_KEYS)
        else
            prepareStatement(sql)
        stmt.use {
            return it.block()
        }
    }

    override fun add(item: Item): Item {
        val sql = "insert into items(name) values (?)"
        connection.withStatement(sql, true) {
            setString(1, item.name)
            executeUpdate()
            generatedKeys.use { keys ->
                if (keys.next()) item.id = keys.getInt(1)
            }
        }
        return item
    }

    override fun replace(id: Int, item: Item): Boolean {
        val sql = "update items set name = ? where id = ?"
        connection.withStatement(sql) {
            setString(1, item.name)
            setInt(2, id)
            return executeUpdate() > 0
        }
    }

    override fun delete(id: Int): Boolean {
        val sql = "delete from items where id = ?"
        connection.withStatement(sql) {
            setInt(1, id)
            return executeUpdate() > 0
        }
    }

    override fun findAll(): List<Item> {
        val sql = "select * from items"
        connection.withStatement(sql) {
            executeQuery().use { rs ->
                val items = mutableListOf<Item>()
                while (rs.next()) {
                    items.add(Item(rs.getInt("id"), rs.getString("name")))
                }
                return items
            }
        }
    }

    override fun findByName(key: String): List<Item> {
        val sql = "select * from items where name = ?"
        connection.withStatement(sql) {
            setString(1, key)
            executeQuery().use { rs ->
                val items = mutableListOf<Item>()
                while (rs.next()) {
                    items.add(Item(rs.getInt("id"), rs.getString("name")))
                }
                return items
            }
        }
    }

    override fun findById(id: Int): Item? {
        val sql = "select * from items where id = ?"
        connection.withStatement(sql) {
            setInt(1, id)
            executeQuery().use { rs ->
                return if (rs.next()) {
                    Item(id, rs.getString("name"))
                } else null
            }
        }
    }

    override fun close() {
        connection.close()
    }
}