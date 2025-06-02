package ru.job4j.condition

class UserAction private constructor() {
    companion object {
        fun showMenu(actions: List<String>) {
            println("Menu.")
            actions.forEachIndexed { index, action -> println("$index. $action") }
        }

        fun createAction(tracker: Tracker) {
            println("=== Create a new Item ====")
            print("Enter name: ")
            val name = readln()
            val item = Item(name = name)
            tracker.add(item)
        }

        fun deleteAction(tracker: Tracker) {
            println("=== Delete item ===")
            print("Enter Id: ")
            val id = readln().toIntOrNull()
            when {
                id == null -> println("Id must be a number")
                tracker.delete(id) -> println("Request successfully deleted")
                else -> println("Mistake. Request not found")
            }
        }

        fun showAll(tracker: Tracker) {
            println("=== Show all items ===")
            val items = tracker.findAll()
            if (items.isNotEmpty()) {
                items.forEach { elem -> println(elem) }
            } else {
                println("No requests found")
            }
        }
    }
}