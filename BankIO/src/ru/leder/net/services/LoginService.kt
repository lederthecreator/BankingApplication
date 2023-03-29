package ru.leder.net.services

import com.google.gson.Gson
import ru.leder.net.entities.User
import ru.leder.net.operations.UserOperations
import ru.leder.net.utils.Logger
import ru.leder.net.utils.Utils

class LoginService(private val logger: Logger) {
    private val gson = Gson()
    fun execute(data: Any?) : User? {
        val model = getData(data)

        return processModel(model)
    }

    private fun getData(data: Any?) : LoginModel {
        val jsonData = gson.toJson(data)
        val parsedData = Utils.getMapFromJson(jsonData)

        val login = parsedData["login"].toString()
        val password = parsedData["password"].toString()

        return LoginModel(login, password)
    }

    private fun processModel(loginModel: LoginModel) : User? {
        if (loginModel.login.isEmpty() || loginModel.password.isEmpty()) {
            return null
        }

        val user = UserOperations.getByLogin(loginModel.login)
        if (user == null)
        {
            logger.log("Login is incorrect")
            return null
        }
        else if (user.password != loginModel.password)
        {
            logger.log("Password is incorrect")
            return null
        }

        return user
    }

    private data class LoginModel(
        val login: String,
        val password: String
    )
}