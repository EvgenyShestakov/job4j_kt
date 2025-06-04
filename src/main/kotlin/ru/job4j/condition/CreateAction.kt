package ru.job4j.condition

class CreateAction(val output: Output) : UserAction {
    override fun execute(tracker: Tracker, input: Input): Boolean {
        output.println("=== Create a new Item ====")
        val name = input.ascString("Enter name: ")
        val item = Item(name = name)
        tracker.add(item)
        return true
    }

    override fun getName(): String = "Create"
}