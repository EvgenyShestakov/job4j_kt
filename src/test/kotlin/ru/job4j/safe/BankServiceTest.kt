package ru.job4j.safe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BankServiceTest {

    @Test
    fun whenTransferMoney() {
        val bankService = BankService()
        val vasia = User("6573 867345", "Vasia Pupkin")
        val vasiaAccount = Account("587563215698745895321", 564000.37)
        val tolia = User("3568 435698", "Tolia Mupkin")
        val toliaAccount = Account("462851379564513568259", 10.01)
        bankService.addUser(vasia)
        bankService.addAccount("6573 867345", vasiaAccount)
        bankService.addUser(tolia)
        bankService.addAccount("3568 435698", toliaAccount)
        val resultOperation = bankService.transferMoney(
            "6573 867345", "587563215698745895321",
            "3568 435698", "462851379564513568259",
            347000.0
        )
        assertThat(resultOperation).isTrue
        bankService.findByRequisite(
            "6573 867345", "587563215698745895321"
        )
            ?.let { assertThat(it.balance) }?.isEqualTo(217000.37)
        bankService.findByRequisite(
            "3568 435698", "462851379564513568259"
        )
            ?.let { assertThat(it.balance) }?.isEqualTo(347010.01)
    }
}