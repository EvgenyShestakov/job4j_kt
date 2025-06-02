package ru.job4j.condition

fun main() {
    val tracker = Tracker()
    val actions = listOf("Create", "ShowAll", "Delete", "Exit")
    init(tracker, actions)
}

fun init(tracker: Tracker, actions: List<String>) {
    while (true) {
        UserAction.showMenu(actions)
        print("Select number of menu: ")
        val select = readln().toIntOrNull()
        when (select) {
            0 -> UserAction.createAction(tracker)
            1 -> UserAction.showAll(tracker)
            2 -> UserAction.deleteAction(tracker)
            3 -> break
            else -> {
                println("Wrong input, you can select: 0 .. 3")
            }
        }
    }
}