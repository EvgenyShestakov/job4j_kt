package ru.job4j.condition

class Tracker {
    private val items = mutableListOf<Item>()
    private var ids = 1

    fun add(item: Item): Item {
        item.id = ids++
        items.add(item)
        return item
    }

    fun findAll(): List<Item> {
        return items
    }

    fun findById(id: Int): Item? {
        val index = indexOf(id)
        return if (index != -1) items[index] else null
    }

    fun findByName(key: String): List<Item> {
        return items.filter { item -> item.name == key }
    }

    fun replace(id: Int, replacement: Item): Boolean {
        val index = indexOf(id)
        return if (index != -1) {
            replacement.id = id
            items[index] = replacement
            true
        } else false
    }

    fun delete(id: Int): Boolean {
        val index = indexOf(id)
        return if (index != -1) {
            items.removeAt(index)
            true
        } else false
    }

    private fun indexOf(id: Int): Int =
        items.indexOfFirst { it.id == id }
}
