package ru.job4j.condition

class Tracker {
    private val items = ArrayList<Item>();
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
        val names = ArrayList<Item>()
        for (index in 0 until items.size) {
            val item = items[index]
            if (item.name == key) {
                names.add(item)
            }
        }
        return names
    }

    fun replace(id: Int, replacement: Item): Boolean {
        val index = indexOf(id)
        val rsl = index != -1
        if (rsl) {
            replacement.id = id
            items[index] = replacement
        }
        return rsl;
    }

    fun delete(id: Int): Boolean {
        val index = indexOf(id)
        val rsl = index != -1
        if (rsl) {
            items.removeAt(index);
        }
        return rsl;
    }

    private fun indexOf(id: Int): Int {
        var rsl = -1
        for (index in 0 until items.size) {
            if (items[index].id == id) {
                rsl = index;
                break
            }
        }
        return rsl
    }
}