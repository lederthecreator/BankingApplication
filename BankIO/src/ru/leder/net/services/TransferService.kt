package ru.leder.net.services

import com.google.gson.Gson
import org.jetbrains.exposed.sql.transactions.transaction
import ru.leder.net.entities.BankAccount
import ru.leder.net.entities.Transaction
import ru.leder.net.operations.BankAccountOperations
import ru.leder.net.operations.UserOperations
import ru.leder.net.utils.Logger
import ru.leder.net.utils.Utils
import java.math.BigDecimal

class TransferService(private val logger: Logger) {
    private val gson = Gson()
    fun execute (data: Any) : Boolean {
        val model = getData(data) ?: return false

        when (model.type) {
            "Withdraw" -> {
                return  processWithdraw(model)
            }

            "Deposit" -> {
                return processDeposit(model)
            }

            "Transfer" -> {
                return processTransfer(model)
            }
        }

        return false
    }

    private fun processTransfer(model: CacheModel): Boolean {
        transaction {
            model.initiator.balance -= model.amount
            model.recipient!!.balance += model.amount
        }

        transaction {
            Transaction.new {
                // transactionCreateDate = LocalDateTime.now()
                initiatorBankAccountId = model.initiator.id
                recipientBankAccountId = model.recipient!!.id
                amount = model.amount
            }
        }

        return true
    }

    private fun processDeposit(model: CacheModel): Boolean {
        transaction {
            model.initiator.balance += model.amount
        }

        transaction {
            Transaction.new {
                // transactionCreateDate = LocalDateTime.now()
                initiatorBankAccountId = model.initiator.id
                recipientBankAccountId = model.initiator.id
                amount = model.amount
            }
        }

        return true
    }

    private fun processWithdraw(model: CacheModel): Boolean {
        transaction {
            model.initiator.balance -= model.amount
        }

        transaction {
            Transaction.new {
                // transactionCreateDate = LocalDateTime.now()
                initiatorBankAccountId = model.initiator.id
                recipientBankAccountId = model.initiator.id
                amount = model.amount
            }
        }

        return true
    }

    private fun getData(data: Any) : CacheModel? {
        val jsonData = gson.toJson(data)
        val mapData = Utils.getMapFromJson(jsonData)

        try {
            val initiatorId = mapData["initiator"].toString().toInt()
            val initiatorAccountNumber = mapData["initiatorBankAccount"].toString()
            val recipientLogin = mapData["recipient"].toString()
            val recipientAccountNumber = mapData["recipientBankAccount"].toString()
            val amount = mapData["amount"].toString().toBigDecimal()
            val type = mapData["type"].toString()

            val userInitiator = UserOperations.get(initiatorId)
            val userRecipient = UserOperations.getByLogin(recipientLogin)

            if (userInitiator == null) {
                throw Exception("Cannot find initiator")
            }

            val initiatorBankAccount = BankAccountOperations.getByUserAndNumber(userInitiator, initiatorAccountNumber)
            if (initiatorBankAccount == null) {
                throw Exception("Cannot find initiator bank account")
            }

            var recipientBankAccount: BankAccount? = null

            if (type == "Transfer") {
                if (userRecipient == null) {
                    throw Exception("Cannot find recipient")
                }

                recipientBankAccount = BankAccountOperations.getByUserAndNumber(userRecipient, recipientAccountNumber)
                if (recipientBankAccount == null) {
                    throw Exception("Cannot find recipient bank account")
                }
            } else {
                recipientBankAccount = initiatorBankAccount
            }

            if (amount < BigDecimal(0)) {
                throw Exception("Amount cannot be less than zero")
            }

            return CacheModel(
                initiatorBankAccount, recipientBankAccount, amount, type
            )

        } catch (ex: Throwable) {
            logger.log("Cannot parse data, ex.message: ${ex.localizedMessage}")
        }

        return null
    }

    private data class CacheModel(
        val initiator: BankAccount,
        val recipient: BankAccount?,
        val amount: BigDecimal,
        val type: String
    )
}