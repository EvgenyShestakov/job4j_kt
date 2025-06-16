package ru.job4j.dsl

import ru.job4j.condition.Item
import ru.job4j.condition.Tracker

fun Item.save(tracker: Tracker) {
    tracker.add(this)
}

fun main() {
    val tracker = Tracker()
    val item = Item()
    item.name = "item1"
    item.save(tracker)
    println(tracker.findById(item.id))
}