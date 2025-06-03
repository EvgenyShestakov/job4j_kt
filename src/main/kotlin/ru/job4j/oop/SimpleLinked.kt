package ru.job4j.oop

class SimpleLinked<T> : Iterable<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0
    private var modCount = 0

    fun size(): Int = size

    fun add(value: T) {
        val newNode: Node<T> = Node(
            value = value,
        )

        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }

        size++
        modCount++
    }

    operator fun get(index: Int): T {
        if(index !in 0 until size) {
            throw IndexOutOfBoundsException("Index $index out of bound for size $size")
        }
        val iterator: Iterator<T> = this.iterator()
        repeat(index) { iterator.next() }
        return iterator.next()
    }

    override fun iterator(): Iterator<T> = SimpleLinkedIterator()

    private inner class SimpleLinkedIterator : Iterator<T> {
        private var cursor: Node<T>? = null
        private val expectedModCount = modCount

        override fun hasNext(): Boolean = cursor != tail

        override fun next(): T {
            if (!hasNext()) throw NoSuchElementException()
            if (expectedModCount != modCount) throw ConcurrentModificationException()

            cursor = cursor?.next ?: head
            return cursor?.value ?: error("Cursor value is null")
        }
    }

    private data class Node<K>(
        val value: K,
        var next: Node<K>? = null,
    )
}