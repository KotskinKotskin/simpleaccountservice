package DAO

import model.Account
import java.util.concurrent.atomic.AtomicInteger

class AccountDAO {
    var account = hashMapOf(
        0 to Account(id = 1, balance = 0.00)
    )
    var lastId: AtomicInteger = AtomicInteger(account.size - 1)

    fun save(id: Int, balance: Double) {
        val id = lastId.incrementAndGet()
        account[id] = Account(id = id, balance = balance)
    }

    fun findById(id: Int): Account? {
        return account[id]
    }

    fun operation(id: Int, amount: Double, operation: String) {
        var currentBalance = account[id]?.balance
        if (operation == "+")
        {
            if (currentBalance != null) {
                currentBalance =   currentBalance + amount
            }
        }
        if (operation == "-")
        {
            if (currentBalance != null) {
                currentBalance =   currentBalance - amount
            }
        }
        account[id] = Account(id = id, balance = currentBalance)

    }


}
