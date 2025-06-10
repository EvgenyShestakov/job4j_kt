package ru.job4j.condition

fun main() {
    val tracker = Tracker()
    val output = ConsoleOutput()
    val input = ConsoleInput(output)

    val userActions: Map<Int, Pair<String, (Tracker, Input, Output) -> Boolean>> = linkedMapOf(
        0 to ("Create" to createAction()),
        1 to ("Delete" to deleteAction()),
        2 to ("ShowAll" to showAllAction()),
        3 to ("Exit" to exitAction())
    )

    StartUI(output).init(tracker, userActions, input)
}

