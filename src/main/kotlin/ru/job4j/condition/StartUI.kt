package ru.job4j.condition

class StartUI(val output: Output) {

    fun init(tracker: Tracker, actions: List<UserAction>, input: Input) {
        var run: Boolean = true
        while (run) {
            showMenu(actions)
            val select = input.ascInt("Select number of menu: ")
            if (select == null || select !in actions.indices) {
                output.println("Wrong input, you can select: 0 ${actions.size - 1}")
                continue
            }
            run = actions[select].execute(tracker, input)
        }
    }

    private fun showMenu(actions: List<UserAction>) {
        output.println("Menu.")
        actions.forEachIndexed { index, action -> println("$index. ${action.getName()}") }
    }
}