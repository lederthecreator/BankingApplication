package ru.leder.net

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import ru.leder.net.entities.BankAccounts
import ru.leder.net.entities.Users
import ru.leder.net.operations.BankAccountOperations
import ru.leder.net.operations.UserOperations

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

                SchemaUtils.create(Users, BankAccounts)

                println(UserOperations.getAllQuery())
                println(BankAccountOperations.getAllQuery())
            }

            return true
        } catch (ex: Exception)
        {
            println(ex.message)
            return false
        }
    }
}