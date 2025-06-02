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
        val ok = index != -1
        if (!ok) {
            return false
        }
        replacement.id = id
        items[index] = replacement
        return true
    }

    fun delete(id: Int): Boolean {
        val index = indexOf(id)
        val ok = index != -1
        if (!ok) {
            return false
        }
        items.removeAt(index)
        return true
    }

    private fun indexOf(id: Int): Int =
        items.indexOfFirst { it.id == id }
}
