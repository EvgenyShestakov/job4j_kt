package ru.job4j.condition

import java.time.LocalDateTime

data class Item(var id: Int = 0, var name: String = "", val createdBy: LocalDateTime = LocalDateTime.now())

