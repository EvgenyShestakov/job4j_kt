package ru.job4j.condition

class ExitAction(val output: Output) : UserAction {
    override fun execute(tracker: Tracker, input: Input): Boolean {
        output.println("=== Exit ===")
        return false
    }

    override fun getName(): String = "Exit"
}