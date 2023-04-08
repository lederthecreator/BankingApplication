package ru.leder.net.entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.math.BigDecimal
import java.time.LocalDateTime

object Transactions : IntIdTable() {
    val transactionCreateDate = datetime("object_create_date").defaultExpression(CurrentDateTime)

    val initiatorBankAccountId = reference("initiator_bank_account_id", BankAccounts.id, onDelete = ReferenceOption.SET_NULL)

    val recipientBankAccountId = reference("recipient_bank_account_id", BankAccounts.id, onDelete = ReferenceOption.SET_NULL)

    val amount = decimal("amount", 10, 3)
}

class Transaction(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Transaction>(Transactions)

    var transactionCreateDate by Transactions.transactionCreateDate

    var initiatorBankAccountId by Transactions.initiatorBankAccountId

    var recipientBankAccountId by Transactions.recipientBankAccountId

    var amount by Transactions.amount
}

data class TransactionSimplified(
    val transactionCreateDate: LocalDateTime,
    val recipientLogin: String,
    val recipientBankAccount: String,
    val amount: BigDecimal
) {
    override fun toString(): String {
        return "[$transactionCreateDate] $recipientLogin $recipientBankAccount $amount"
    }
}