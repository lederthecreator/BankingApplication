package ru.leder.net.services

import com.google.gson.Gson
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import ru.leder.net.entities.User
import ru.leder.net.operations.UserOperations
import ru.leder.net.utils.Logger
import ru.leder.net.utils.Utils

class SignUpService(private val logger: Logger) {
    private val gson = Gson()

    fun execute(data: Any?): User? {
        val model = getData(data)

        return process(model)
    }

    private fun getData(data: Any?) : SignUpModel {
        val jsonData = gson.toJson(data)
        val dataMap = Utils.getMapFromJson(jsonData)

        val name = dataMap["name"].toString()
        val login = dataMap["login"].toString()
        val password = dataMap["password"].toString()

        return SignUpModel(
            name, login, password
        )
    }

    private fun process(model: SignUpModel) : User? {
        if (model.login.isEmpty()) {
            logger.log("Login is empty")
            return null
        }

        if (model.password.isEmpty()) {
            logger.log("Password is empty")
            return null
        }

        if (model.name.isEmpty()) {
            logger.log("Name is empty")
            return null
        }

        if (UserOperations.getByLogin(model.login) != null) {
            logger.log("Cannot create account with this login")
            return null
        }

        var user: User? = null

        transaction {
            addLogger(StdOutSqlLogger)

            user = User.new {
                login = model.login
                password = model.password
                name = model.name
            }
        }

        return user;
    }

    private data class SignUpModel (
        val name: String,
        val login: String,
        val password: String
    )
}