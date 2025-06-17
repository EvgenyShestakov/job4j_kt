package ru.job4j.dsl

import org.assertj.core.api.Assertions.assertThat

infix fun <T> T.eq(expected: T) {
    assertThat(this).isEqualTo(expected)
}

infix fun <T> T.notEq(expected: T) {
    assertThat(this).isNotEqualTo(expected)
}

inline infix fun <reified T> Collection<T>.contains(expected: Collection<T>) {
    assertThat(this).containsAll(expected)
}
