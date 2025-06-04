package ru.job4j.condition

fun main() {
    val tracker = Tracker()
    val output = ConsoleOutput()
    val input = ConsoleInput(output)
    val actions = listOf(
        CreateAction(output),
        ShowAllAction(output),
        DeleteAction(output),
        ExitAction(output)
    )
    StartUI(output).init(tracker, actions, input)
}