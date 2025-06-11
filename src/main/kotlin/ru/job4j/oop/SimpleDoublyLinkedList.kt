package ru.job4j.oop

class SimpleDoublyLinkedList<T> : Iterable<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0
    private var modCount = 0

    fun size(): Int = size

    fun add(value: T) {
        val newNode = Node(
            value = value, prev = tail
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
        if (index !in 0 until size) {
            throw IndexOutOfBoundsException("Index $index out of bound for size $size")
        }
        var node = head
        repeat(index) { node = node?.next }
        return node!!.value
    }

    override fun iterator(): Iterator<T> = SimpleLinkedListIterator()

    private inner class SimpleLinkedListIterator : ListIterator<T> {
        private var cursor: Node<T>? = head
        private var lastReturned: Node<T>? = null
        private var currentIndex = 0
        private val expectedModCount = modCount

        override fun hasNext() = cursor != null

        override fun hasPrevious() = (cursor?.prev != null) || (cursor == null && tail != null)

        override fun next(): T {
            if (!hasNext()) throw NoSuchElementException()
            if (expectedModCount != modCount) throw ConcurrentModificationException()
            lastReturned = cursor
            cursor = cursor?.next
            currentIndex++
            return lastReturned!!.value
        }

        override fun previous(): T {
            if (!hasPrevious()) throw NoSuchElementException()
            if (expectedModCount != modCount) throw ConcurrentModificationException()
            cursor = if (cursor == null) tail else cursor!!.prev
            lastReturned = cursor
            currentIndex--
            return lastReturned!!.value
        }

        override fun nextIndex(): Int = currentIndex

        override fun previousIndex(): Int = currentIndex - 1
    }

    private data class Node<K>(
        val value: K,
        var next: Node<K>? = null,
        var prev: Node<K>? = null,
    )
}