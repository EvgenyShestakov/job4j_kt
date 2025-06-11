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
        srcPassport: String, srcRequisite: String,
        destPassport: String, destRequisite: String, amount: Double,
    ): Boolean {
        val source = findByRequisite(srcPassport, srcRequisite)
        val dest = findByRequisite(destPassport, destRequisite)
        val rsl = source != null && dest != null && source.balance > amount
        if (rsl) {
            source.balance -= amount
            dest.balance += amount
        }
        return rsl
    }
}
