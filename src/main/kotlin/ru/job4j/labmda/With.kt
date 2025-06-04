package ru.job4j.labmda

fun main() {
    val message = Message(username = "Victor", email = "pink_rabit765@google.com")
    println(emailTo(message))
}

fun emailTo(message: Message): String {
    with(StringBuilder()) {
        append("Subject : ").append("${message.username}, ")
        append("Body : ").append("Hello, ").append(message.email).append(", ")
        append("You win!")
        return toString()
    }
}

data class Message(val username: String, val email: String)