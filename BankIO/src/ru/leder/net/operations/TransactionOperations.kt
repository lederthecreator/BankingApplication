package ru.leder.net.operations

import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import ru.leder.net.entities.Transactions
import ru.leder.net.entities.Transaction as TransactionEntity

object TransactionOperations {
    fun getByBankAccount(id: Int) : List<TransactionEntity> {
        var transactionList: List<TransactionEntity> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)

            transactionList = TransactionEntity.find {
                Transactions.initiatorBankAccountId eq id or (Transactions.recipientBankAccountId eq id)
            }.toList()
        }

        return transactionList
    }
}