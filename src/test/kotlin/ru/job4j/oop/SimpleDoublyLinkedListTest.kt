package ru.job4j.oop

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class SimpleDoublyLinkedListTest {

    @Test
    fun whenAddAndSize() {
        val list = SimpleDoublyLinkedList<Int>()
        assertThat(list.size()).isEqualTo(0)
        list.add(1)
        list.add(2)
        assertThat(list.size()).isEqualTo(2)
    }

    @Test
    fun whenGetByIndex() {
        val list = SimpleDoublyLinkedList<String>()
        list.add("foo")
        list.add("bar")
        assertThat(list[0]).isEqualTo("foo")
        assertThat(list[1]).isEqualTo("bar")
    }

    @Test
    fun whenIterateForwardAndBackwardThenCorrectOrder() {
        val list = SimpleDoublyLinkedList<String>()
        list.add("x")
        list.add("y")
        list.add("z")
        val it = list.iterator() as ListIterator<String>

        assertThat(it.hasNext()).isTrue()
        assertThat(it.next()).isEqualTo("x")
        assertThat(it.hasNext()).isTrue()
        assertThat(it.next()).isEqualTo("y")
        assertThat(it.hasNext()).isTrue()
        assertThat(it.next()).isEqualTo("z")
        assertThat(it.hasNext()).isFalse()

        assertThat(it.hasPrevious()).isTrue()
        assertThat(it.previous()).isEqualTo("z")
        assertThat(it.hasPrevious()).isTrue()
        assertThat(it.previous()).isEqualTo("y")
        assertThat(it.hasPrevious()).isTrue()
        assertThat(it.previous()).isEqualTo("x")
        assertThat(it.hasPrevious()).isFalse()
    }

    @Test
    fun whenIteratorNextAfterEndThenNoSuchElementException() {
        val list = SimpleDoublyLinkedList<String>()
        list.add("1")
        val it = list.iterator() as ListIterator<String>
        it.next()
        assertThat(it.hasNext()).isFalse()
        assertThatThrownBy {
            it.next()
        }.isInstanceOf(NoSuchElementException::class.java)
    }

    @Test
    fun whenIteratorPreviousBeforeStartThenNoSuchElementException() {
        val list = SimpleDoublyLinkedList<String>()
        list.add("1")
        val it = list.iterator() as ListIterator<String>
        it.next()
        it.previous()
        assertThat(it.hasPrevious()).isFalse()
        assertThatThrownBy {
            it.previous()
        }.isInstanceOf(NoSuchElementException::class.java)
    }
}