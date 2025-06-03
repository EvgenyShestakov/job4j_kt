package ru.job4j.oop

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.IndexOutOfBoundsException
import java.util.NoSuchElementException

class SimpleLinkedTest {

    @Test
    fun whenAddThenGet() {
        val linkedList = SimpleLinked<Int>()
        linkedList.add(5)
        linkedList.add(15)
        assertThat(linkedList[0]).isEqualTo(5)
        assertThat(linkedList[1]).isEqualTo(15)
    }

    @Test
    fun whenAddThenReturnIterator() {
        val linkedList = SimpleLinked<Int>()
        linkedList.add(5)
        linkedList.add(15)
        val iterator = linkedList.iterator()
        assertThat(iterator.next()).isEqualTo(5)
        assertThat(iterator.next()).isEqualTo(15)
    }

    @Test
    fun whenAddThenReturnSize() {
        val linkedList = SimpleLinked<Int>()
        linkedList.add(5)
        linkedList.add(15)
        assertThat(linkedList.size()).isEqualTo(2)
    }

    @Test
    fun whenGetThenIndexOutOfBoundException() {
        val linkedList = SimpleLinked<Int>()
        Assertions.assertThatThrownBy {
            linkedList[0]
        }.isInstanceOf(IndexOutOfBoundsException::class.java)
    }

    @Test
    fun whenReturnIteratorThenIndexOutOfBoundException() {
        val linkedList = SimpleLinked<Int>()
        val iterator = linkedList.iterator()
        Assertions.assertThatThrownBy {
            iterator.next()
        }.isInstanceOf(NoSuchElementException::class.java)
    }

    @Test
    fun whenReturnIteratorThenConcurrentModificationException() {
        val linkedList = SimpleLinked<Int>()
        val iterator = linkedList.iterator()
        linkedList.add(5)
        Assertions.assertThatThrownBy {
            iterator.next()
        }.isInstanceOf(ConcurrentModificationException::class.java)
    }
}