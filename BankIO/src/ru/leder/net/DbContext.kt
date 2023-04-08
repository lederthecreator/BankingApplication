package ru.leder.net

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import ru.leder.net.entities.BankAccounts
import ru.leder.net.entities.Transactions
import ru.leder.net.entities.Users

class DbContext {

    private lateinit var _connection : Database;

    init {
        if (connect()) {
            transaction {
                addLogger(StdOutSqlLogger)

            }
        }
    }
    private fun connect() : Boolean{
        try {
            Database.connect("jdbc:pgsql://localhost:5432/postgres", driver = "com.impossibl.postgres.jdbc.PGDriver",
                user = "postgres", password = "root")

            transaction {
                addLogger(StdOutSqlLogger)

                SchemaUtils.create(Users, BankAccounts, Transactions)
            }

            return true
        } catch (ex: Exception)
        {
            println(ex.message)
            return false
        }
    }
}