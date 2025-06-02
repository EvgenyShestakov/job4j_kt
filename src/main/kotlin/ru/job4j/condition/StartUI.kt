package ru.job4j.condition

fun main() {
    val tracker = Tracker()
    val actions = listOf("Create", "ShowAll", "Delete", "Exit")
    init(tracker, actions)
}

fun init(tracker: Tracker, actions: List<String>) {
    while (true) {
        showMenu(actions)
        print("Select number of menu: ")
        val select = readln().toIntOrNull()
        when (select) {
            0 -> createAction(tracker)
            1 -> showAll(tracker)
            2 -> deleteAction(tracker)
            3 -> break
            else -> {
                println("Wrong input, you can select: 0 .. 3")
            }
        }
    }
}

private fun showMenu(actions: List<String>) {
    println("Menu.")
    actions.forEachIndexed { index, action -> println("$index. $action") }
}

private fun createAction(tracker: Tracker) {
    println("=== Create a new Item ====")
    print("Enter name: ")
    val name = readln()
    val item = Item(name = name)
    tracker.add(item)
}

private fun deleteAction(tracker: Tracker) {
    println("=== Delete item ===")
    print("Enter Id: ")
    val id = readln().toIntOrNull()
    when {
        id == null -> println("Id must be a number")
        tracker.delete(id) -> println("Request successfully deleted")
        else -> println("Mistake. Request not found")
    }
}

private fun showAll(tracker: Tracker) {
    println("=== Show all items ===")
    val items = tracker.findAll()
    if (items.isNotEmpty()) {
        items.forEach { elem -> println(elem) }
    } else {
        println("No requests found")
    }
}