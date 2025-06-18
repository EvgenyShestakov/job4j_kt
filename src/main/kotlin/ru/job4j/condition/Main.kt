package ru.job4j.condition

fun main() {
    val tracker = Tracker()
    val output = ConsoleOutput()
    val input = ConsoleInput(output)

    val userActions: Map<Int, Pair<String, () -> Boolean>> = linkedMapOf(
        0 to ("Create" to createAction(tracker, input, output)),
        1 to ("Delete" to deleteAction(tracker, input, output)),
        2 to ("ShowAll" to showAllAction(tracker, output)),
        3 to ("Exit" to exitAction(output))
    )

    StartUI(output).init(userActions, input)
}

