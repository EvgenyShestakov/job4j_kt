package ru.job4j.safe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Properties

class RepositoryTest {
    private lateinit var repository: Repository
    private lateinit var cfg: Properties

    @BeforeEach
    fun setUp() {
        cfg = Properties()
        val loader = RepositoryTest::class.java.classLoader
        loader.getResourceAsStream("db.properties").use { io ->
            requireNotNull(io) { "Файл db.properties не найден!" }
            cfg.load(io)
        }
        repository = Repository()
        repository.init(cfg)
    }

    @Test
    fun whenCreateTableAndInsertThenSelect() {
        val createLog = repository.exec("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(100))")
        assertThat(createLog).contains("Выполнено")

        val insertLog = repository.exec("INSERT INTO users(id, name) VALUES (1, 'Alex')")
        assertThat(insertLog).contains("Выполнено")

        val result = repository.exec("SELECT * FROM users")
        assertThat(result).contains("Выполнено")
    }

    @Test
    fun whenUpdateAndDelete() {
        repository.exec("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(100))")
        repository.exec("INSERT INTO users(id, name) VALUES (2, 'Bob')")
        val updateLog = repository.exec("UPDATE users SET name='Bobby' WHERE id=2")
        assertThat(updateLog).contains("Выполнено")
        val deleteLog = repository.exec("DELETE FROM users WHERE id=2")
        assertThat(deleteLog).contains("Выполнено")
    }

    @Test
    fun whenInvalidQueryThenThrow() {
        val exception = org.assertj.core.api.Assertions.catchThrowable {
            repository.exec("SELECT * FROM not_exist_table")
        }
        assertThat(exception).isInstanceOf(IllegalStateException::class.java)
    }
}