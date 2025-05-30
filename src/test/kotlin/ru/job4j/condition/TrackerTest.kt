package ru.job4j.condition

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TrackerTest {

    @Test
    fun whenAddNewItemThenTrackerHasSameItem() {
        val tracker = Tracker()
        val item = Item()
        item.name = "item1"
        tracker.add(item)
        val result = tracker.findById(item.id)
        assertThat(result?.name).isEqualTo(item.name)
    }

    @Test
    fun whenReplace() {
        val tracker = Tracker()
        val bug = Item()
        bug.name = "Bug"
        tracker.add(bug)
        val id = bug.id
        val bugWithDesc = Item()
        bugWithDesc.name = "Bug with description"
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id)?.name).isEqualTo("Bug with description")
    }

    @Test
    fun whenDelete() {
        val tracker = Tracker()
        val bug = Item()
        bug.name = "Bug"
        tracker.add(bug)
        val id = bug.id
        tracker.delete(id);
        assertThat(tracker.findById(id)).isNull()
    }

    @Test
    fun whenFindAll() {
        val tracker = Tracker()
        val item1 = Item()
        val item2 = Item()
        tracker.add(item1)
        tracker.add(item2)
        assertThat(tracker.findAll().size).isEqualTo(2)
    }

    @Test
    fun whenFindByName() {
        val tracker = Tracker()
        val item = Item()
        item.name = "item1"
        tracker.add(item)
        val result = tracker.findByName(item.name)
        assertThat(result[0].name).isEqualTo(item.name)
    }
}