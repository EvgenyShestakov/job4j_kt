package ru.job4j.condition

class StartUI(val output: Output) {

    fun init(userActions: Map<Int, Pair<String, () -> Boolean>>, input: Input) {
        var run: Boolean = true
        while (run) {
            showMenu(userActions.values.toList())
            val select = input.ascInt("Select number of menu: ")
            if (select == null || select !in userActions.keys) {
                output.println("Wrong input, you can select: 0 ${userActions.size - 1}")
                continue
            }
            run = userActions[select]?.second?.invoke() != false
        }
    }

    private fun showMenu(userActions: List<Pair<String, () -> Boolean>>) {
        output.println("Menu.")
        userActions.forEachIndexed { index, action -> println("$index. ${action.first}") }
    }
}