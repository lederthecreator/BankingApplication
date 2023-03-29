package ru.leder.net.services

import com.google.gson.Gson
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import ru.leder.net.operations.BankAccountOperations
import ru.leder.net.operations.UserOperations
import ru.leder.net.utils.Logger
import ru.leder.net.utils.Utils
import java.math.BigDecimal

class TransferService(private val logger: Logger) {
    private val gson = Gson()
    fun execute (data: Any) : Boolean {
        val model = getData(data) ?: return false

        return process(model)
    }

    private fun getData(data: Any) : TransferModel? {
        val jsonData = gson.toJson(data)
        val mapData = Utils.getMapFromJson(jsonData)
        var transferModel: TransferModel? = null

        try {
            val initiatorId = mapData["initiator"].toString().toInt()
            val initiatorAccountNumber = mapData["initiatorBankAccount"].toString()
            val recipientId = mapData["recipient"].toString().toInt()
            val recipientAccountNumber = mapData["recipientBankAccount"].toString()
            val amount = mapData["amount"].toString().toBigDecimal()

            transferModel = TransferModel(
                initiatorId, initiatorAccountNumber, recipientId, recipientAccountNumber, amount
            )
        } catch (ex: Throwable) {
            logger.log("Cannot parse data, ex.message: ${ex.localizedMessage}")
        }

        return transferModel
    }

    private fun process(model: TransferModel) : Boolean {
        val userInitiator = UserOperations.get(model.initiatorId)
        if (userInitiator == null) {
            logger.log("Cannot find initiator by id: ${model.initiatorId}")
            return false
        }

        val initiatorBankAccounts = BankAccountOperations.getAllByUserQuery(userInitiator)
        if (initiatorBankAccounts.isEmpty() || initiatorBankAccounts.find { it.number == model.initiatorAccountNumber } == null) {
            logger.log("Cannot find bank account of user ${userInitiator.name} with number ${model.initiatorAccountNumber}")
            return false
        }
        val initiatorBankAccount = initiatorBankAccounts.find { it.number == model.initiatorAccountNumber }!! // Если не нашел - return выше

        if (model.amount < BigDecimal(0)) {
            logger.log("Invalid amount (less than zero)")
            return false
        }

        if (initiatorBankAccount.balance < model.amount) {
            logger.log("Invalid operation. Bank account [${initiatorBankAccount.number}] dont have enough funds")
            return false
        }

        val userRecipient = UserOperations.get(model.recipientId)
        if (userRecipient == null) {
            logger.log("Cannot find recipient by id ${model.recipientId}")
            return false
        }

        val recipientBankAccounts = BankAccountOperations.getAllByUserQuery(userRecipient)
        if (recipientBankAccounts.isEmpty() || recipientBankAccounts.find { it.number == model.recipientAccountNumber } == null) {
            logger.log("Cannot find bank account of user ${userRecipient.name} with number ${model.recipientAccountNumber}")
            return false
        }
        val recipientBankAccount = recipientBankAccounts.find { it.number == model.recipientAccountNumber }!! // Если не нашел - return выше

        transaction {
            addLogger(StdOutSqlLogger)

            initiatorBankAccount.balance -= model.amount
            recipientBankAccount.balance += model.amount
        }

        return true
    }

    private data class TransferModel(
        val initiatorId: Int,
        val initiatorAccountNumber: String,
        val recipientId: Int,
        val recipientAccountNumber: String,
        val amount: BigDecimal
    )
}