package ru.job4j.base

fun main() {
    var names = arrayOfNulls<String>(10)
    names[0] = "Oleg"
    names[3] = "Ivan"
    names[6] = "Anton"
    names[8] = "Nikita"
    defragment(names)
    for ((index, name) in names.withIndex()) {
        println("$index $name")
    }
}

fun defragment(array: Array<String?>) : Array<String?> {
    for (i in 0 .. array.size - 2) {
        for (j in 0 .. array.size - 2 - i) {
            if (array[j] == null && array[j + 1] != null) {
            var temp = array[j + 1]
                array[j + 1] = null
                array[j] = temp
            }
        }
    }
    return array
}
