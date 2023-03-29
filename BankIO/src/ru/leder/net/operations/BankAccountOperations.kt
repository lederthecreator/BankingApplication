package ru.leder.net.operations

import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import ru.leder.net.entities.BankAccount
import ru.leder.net.entities.BankAccounts
import ru.leder.net.entities.User
import java.math.BigDecimal
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


object BankAccountOperations {
    fun create(user: User) : BankAccount? {
        var entity: BankAccount? = null
        val accountNumber = generateNumber()

        transaction {
            entity = BankAccount.new {
                number = accountNumber
                balance = BigDecimal(0)
                userId = user
            }
        }

        return entity
    }

    fun getAllQuery() : List<BankAccount> = BankAccount.all().sortedByDescending { it.id }

    fun getAllByUserQuery(user: User) : List<BankAccount> {
        var list: List<BankAccount> = mutableListOf<BankAccount>().toList()
        transaction {
            list = BankAccount.find {
                BankAccounts.userId eq user.id
            }.toList()
        }

        return list
    }

    fun getByUserAndNumber(user: User, number: String) : BankAccount? {
        var entity: BankAccount? = null

        transaction {
            entity = BankAccount.find {
                BankAccounts.userId eq user.id and (BankAccounts.number eq number)
            }.firstOrNull()
        }

        return entity
    }

    fun delete(bankAccount: BankAccount) {
        transaction {
            addLogger(StdOutSqlLogger)

            bankAccount.delete()
        }
    }

    private fun generateNumber(): String {
        val httpClient = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.randomdatatools.ru/?params=bankNum"))
            .build();

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

        return response.body().subSequence(12, 31).toString();
    }
}