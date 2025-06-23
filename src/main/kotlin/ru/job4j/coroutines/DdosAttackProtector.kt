package ru.job4j.coroutines


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.ConcurrentHashMap

class DdosAttackProtector {

    private val map = ConcurrentHashMap<String, Int>()

    private val mutex = Mutex()

    suspend fun count(ip: String): Int? {
        mutex.withLock {
            return map.getOrDefault(ip, 0)
        }
    }

    suspend fun handleRequest(ip: String) {
        mutex.withLock {
            map.merge(ip, 1) { old, new -> if (old < 100) old + new else old }
        }
    }

    suspend fun startResetJob() {
        repeat(3) {
            delay(5000)
            mutex.withLock {
                map.keys.removeIf {
                    val removed = map[it]?.let { v -> v >= 100 } == true
                    if (removed) println("\nIP $it заблокирован\n")
                    removed
                }
            }
        }
    }
}

fun main() {
    runBlocking {
        with(DdosAttackProtector()) {
            launch() {
                startResetJob()
            }
            val ip = "192.168.1.1"
            repeat(500) { index ->
                launch {
                    delay(index * 30L)
                    handleRequest(ip)
                    println("$index: ip $ip - ${count(ip)}")
                }
            }
        }
    }
}