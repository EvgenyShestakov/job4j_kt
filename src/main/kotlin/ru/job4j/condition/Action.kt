package ru.job4j.condition

fun createAction(tracker: Tracker, input: Input, output: Output): () -> Boolean = {
    output.println("=== Create a new Item ====")
    val name = input.ascString("Enter name: ")
    val item = Item(name = name)
    tracker.add(item)
    true
}

fun deleteAction(tracker: Tracker, input: Input, output: Output): () -> Boolean = {
    output.println("=== Delete item ===")
    val id = input.ascInt("Enter Id: ")
    when {
        id == null -> output.println("Id must be a number")
        tracker.delete(id) -> output.println("Request successfully deleted")
        else -> output.println("Mistake. Request not found")
    }
    true
}

fun showAllAction(tracker: Tracker, output: Output): () -> Boolean = {
    output.println("=== Show all items ===")
    val items = tracker.findAll()
    if (items.isNotEmpty()) {
        items.forEach { elem -> output.println(elem.toString()) }
    } else {
        output.println("No requests found")
    }
    true
}

fun exitAction(output: Output): () -> Boolean = {
    output.println("=== Exit ===")
    false
}