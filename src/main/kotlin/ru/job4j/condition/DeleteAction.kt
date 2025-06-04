package ru.job4j.condition

class DeleteAction(val output: Output) : UserAction {
    override fun execute(tracker: Tracker, input: Input): Boolean {
        output.println("=== Delete item ===")
        val id = input.ascInt("Enter Id: ")
        when {
            id == null -> output.println("Id must be a number")
            tracker.delete(id) -> output.println("Request successfully deleted")
            else -> output.println("Mistake. Request not found")
        }
        return true
    }

    override fun getName(): String = "Delete"
}
