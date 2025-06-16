package ru.job4j.dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import ru.job4j.condition.Item
import ru.job4j.condition.Tracker

class ExtensionFunctionTest {

    @Test
    fun whenItemSave() {
        val tracker = Tracker()
        val expectedItem = Item()
        expectedItem.name = "item1"
        expectedItem.save(tracker)
        val actualItem = tracker.findById(1)
        assertThat(actualItem).isEqualTo(expectedItem)
    }
}