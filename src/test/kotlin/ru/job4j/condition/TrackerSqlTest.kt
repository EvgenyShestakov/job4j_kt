package ru.job4j.condition

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TrackerSqlTest {
    private lateinit var tracker: TrackerSql

    @BeforeEach
    fun setUp() {
        tracker = TrackerSql("db.properties")
        tracker.connection.createStatement().use { it.execute("DELETE FROM items") }
    }

    @AfterEach
    fun tearDown() {
        tracker.close()
    }

    @Test
    fun whenAddNewItemThenTrackerHasSameItem() {
        val item = Item(name = "item1")
        tracker.add(item)
        val result = tracker.findById(item.id)
        assertThat(result?.name).isEqualTo(item.name)
    }

    @Test
    fun whenReplace() {
        val bug = Item(name = "Bug")
        tracker.add(bug)
        val id = bug.id
        val bugWithDesc = Item(name = "Bug with description")
        tracker.replace(id, bugWithDesc)
        assertThat(tracker.findById(id)?.name).isEqualTo("Bug with description")
    }

    @Test
    fun whenDelete() {
        val bug = Item(name = "Bug")
        tracker.add(bug)
        val id = bug.id
        tracker.delete(id)
        assertThat(tracker.findById(id)).isNull()
    }

    @Test
    fun whenFindAll() {
        val item1 = Item(name = "item1")
        val item2 = Item(name = "item2")
        tracker.add(item1)
        tracker.add(item2)
        val all = tracker.findAll()
        assertThat(all.size).isEqualTo(2)
        assertThat(all.map { it.name }).containsExactlyInAnyOrder("item1", "item2")
    }

    @Test
    fun whenFindByName() {
        val item = Item(name = "item1")
        tracker.add(item)
        val result = tracker.findByName("item1")
        assertThat(result).isNotEmpty
        assertThat(result[0].name).isEqualTo("item1")
    }
}
