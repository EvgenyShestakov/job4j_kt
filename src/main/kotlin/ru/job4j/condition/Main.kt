package ru.job4j.condition

fun main() {
    val tracker = Tracker()
    val output = ConsoleOutput()
    val input = ConsoleInput(output)

    val create: (Tracker, Input, Output) -> Boolean = { tracker, input, output ->
        output.println("=== Create a new Item ====")
        val name = input.ascString("Enter name: ")
        val item = Item(name = name)
        tracker.add(item)
        true
    }

    val delete: (Tracker, Input, Output) -> Boolean = { tracker, input, output ->
        output.println("=== Delete item ===")
        val id = input.ascInt("Enter Id: ")
        when {
            id == null -> output.println("Id must be a number")
            tracker.delete(id) -> output.println("Request successfully deleted")
            else -> output.println("Mistake. Request not found")
        }
        true
    }

    val showAll: (Tracker, Input, Output) -> Boolean = { tracker, input, output ->
        output.println("=== Show all items ===")
        val items = tracker.findAll()
        if (items.isNotEmpty()) {
            items.forEach { elem -> println(elem) }
        } else {
            output.println("No requests found")
        }
        true
    }

    val exit: (Tracker, Input, Output) -> Boolean = { tracker, input, output ->
        output.println("=== Exit ===")
        false
    }

    val userActions: Map<Int, Pair<String, (Tracker, Input, Output) -> Boolean>> = linkedMapOf(
        0 to ("Create" to create),
        1 to ("Delete" to delete),
        2 to ("ShowAll" to showAll),
        3 to ("Exit" to exit)
    )

    StartUI(output).init(tracker, userActions, input)
}

