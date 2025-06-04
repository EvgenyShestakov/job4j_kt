package ru.job4j.condition

class ShowAllAction(val output: Output) : UserAction {
    override fun execute(tracker: Tracker, input: Input): Boolean {
        output.println("=== Show all items ===")
        val items = tracker.findAll()
        if (items.isNotEmpty()) {
            items.forEach { elem -> println(elem) }
        } else {
            output.println("No requests found")
        }
        return true
    }

    override fun getName(): String = "ShowAll"
}