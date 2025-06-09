package ru.job4j.safe

class BankService {
    private val users = mutableMapOf<User, MutableList<Account>>()

    fun addUser(user: User) {
        users.getOrPut(user) { mutableListOf() }
    }

    fun findByRequisite(passport: String, requisite: String): Account? {
        return findByPassport(passport)
            ?.let { user ->
                users[user]
                    ?.firstOrNull { it.requisite == requisite }
            }
    }

    fun addAccount(passport: String, account: Account) {
        findByPassport(passport)
            ?.let { user ->
                users[user]
                    ?.add(account)
            }
    }

    fun findByPassport(passport: String): User? {
        return users.keys.firstOrNull() { it.passport == passport }
    }

    fun transferMoney(
        srcPassport: String,
        srcRequisite: String,
        destPassport: String,
        destRequisite: String,
        amount: Double
    ): Boolean = findByRequisite(srcPassport, srcRequisite)?.let { source ->
        findByRequisite(destPassport, destRequisite)?.let { dest ->
            if (source.balance >= amount) {
                source.balance -= amount
                dest.balance += amount
                true
            } else {
                false
            }
        }
    } == true
}
