package ru.job4j.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

data class UserInfoDTO(
    val userData: String,
    val userPreferences: String,
    val userHistory: String,
)

class UserDataService {

    suspend fun fetchUserData(userId: Int): String {
        println("Fetching user data for $userId")
        delay(1000)
        return "User data for $userId"
    }

    suspend fun fetchUserPreferences(userId: Int): String {
        println("Fetching user preferences for $userId")
        delay(1000)
        return "User preferences for $userId"
    }

    suspend fun fetchUserHistory(userId: Int): String {
        println("Fetching user history for $userId")
        delay(1000)
        return "User history for $userId"
    }

    suspend fun fetchUserInfo(userId: Int): UserInfoDTO = coroutineScope {
        val deferred1: Deferred<String> = async { fetchUserData(userId) }
        val deferred2: Deferred<String> = async { fetchUserPreferences(userId) }
        val deferred3: Deferred<String> = async { fetchUserHistory(userId) }
        val results = awaitAll(deferred1, deferred2, deferred3)
        UserInfoDTO(results[0], results[1], results[2])
    }
}

fun main() = runBlocking {
    val service = UserDataService()

    val userInfo = service.fetchUserInfo(1)

    println("UserInfoDTO: $userInfo")
}
