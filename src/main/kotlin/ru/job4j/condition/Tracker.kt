package ru.job4j.condition

class Tracker: Store<Item> {
    private val items = mutableListOf<Item>()
    private var ids = 1

    override fun add(item: Item): Item {
        item.id = ids++
        items.add(item)
        return item
    }

    override fun findAll(): List<Item> {
        return items
    }

    override fun findById(id: Int): Item? {
        val index = indexOf(id)
        return if (index != -1) items[index] else null
    }

    override fun findByName(key: String): List<Item> {
        return items.filter { item -> item.name == key }
    }

    override fun replace(id: Int, replacement: Item): Boolean {
        val index = indexOf(id)
        val ok = index != -1
        if (!ok) {
            return false
        }
        replacement.id = id
        items[index] = replacement
        return true
    }

    override fun delete(id: Int): Boolean {
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
