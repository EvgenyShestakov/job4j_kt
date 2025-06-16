package ru.job4j.condition

interface Store<T> {
    fun add(item: T): Item
    fun replace(id: Int, item: T): Boolean
    fun delete(id: Int): Boolean
    fun findAll(): List<T>
    fun findByName(key: String): List<T>
    fun findById(id: Int): T?
}