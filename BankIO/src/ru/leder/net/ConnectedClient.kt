package ru.leder.net

import com.google.gson.Gson
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ru.leder.net.entities.BankAccountSimplified
import ru.leder.net.entities.User
import ru.leder.net.entities.UserSimplified
import ru.leder.net.operations.BankAccountOperations
import ru.leder.net.operations.UserOperations
import ru.leder.net.response.Response
import ru.leder.net.services.LoginService
import ru.leder.net.services.SignUpService
import ru.leder.net.services.TransferService
import ru.leder.net.utils.Logger
import ru.leder.net.utils.Utils
import java.net.Socket

class ConnectedClient(
    private val socket: Socket
) {
    companion object {
        private val _list = mutableListOf<ConnectedClient>()

        val list: List<ConnectedClient>
            get() = _list.toList()
    }

    private val communicator = Communicator(socket)
    private val gson = Gson()
    private var loggedUser: User? = null
    private val logger = Logger(communicator)

    init {
        _list.add(this)
    }

    suspend fun start()
    {
        coroutineScope {
            launch {
                try {
                    communicator.startReceiving { parse(it) }
                } catch (ex: Throwable) {

                }
            }
        }
    }


    private fun parse (data: String) {
        val request = Utils.getMapFromJson(data)

        when (request["operation"]) {
            "LOGIN" -> {
                if (loggedUser != null) {
                    sendErrorResponse("LOGIN", "You already logged in")
                    return
                }

                val loginService = LoginService(logger)
                val loginData = request["data"]

                if (loginData == null) {
                    sendErrorResponse("LOGIN", "Bad request. Data is empty")
                    return
                }

                val userWithPassword = loginService.execute(loginData)

                if (userWithPassword == null)
                {
                    sendErrorResponse("LOGIN", "Incorrect login or password")
                    return
                }

                val user = UserSimplified(
                    id = userWithPassword.id.value,
                    login = userWithPassword.login,
                    name = userWithPassword.name
                )
                val jsonUser = gson.toJson(user)

                val response = Response(
                    success = true,
                    operation = "LOGIN",
                    data = jsonUser
                )
                val jsonResponse = gson.toJson(response)

                communicator.sendData(jsonResponse)
                loggedUser = userWithPassword
            }

            "SIGNUP" -> {
                val signUpService = SignUpService(logger)
                val signupData = request["data"]

                if (signupData == null) {
                    sendErrorResponse("SIGNUP", "Bad request. Data is empty")
                    return
                }

                val entity = signUpService.execute(signupData)

                if (entity == null) {
                    sendErrorResponse("SIGNUP", "Cannot sign up. Check the data.")
                    return
                }

                val user = UserSimplified(
                    id = entity.id.value,
                    login = entity.login,
                    name = entity.name
                )
                val jsonUser = gson.toJson(user)

                val response = Response(
                    success = true,
                    operation = "SIGNUP",
                    data = jsonUser
                )
                val jsonResponse = gson.toJson(response)

                communicator.sendData(jsonResponse)
                loggedUser = entity
            }

            "LOGOUT" -> {
                if (loggedUser == null) {
                    sendErrorResponse("LOGOUT", "You are not logged in!")
                    return
                }

                loggedUser = null
                val response = Response(
                    success = true,
                    operation = "LOGOUT",
                    data = "Successfully logged out!"
                )
            }

            "GETBANKACCOUNTS" -> {
                if (loggedUser == null)
                {
                    sendErrorResponse("GETBANKACCOUNTS", "You are not logged in")
                    return
                }

                val accountsList = BankAccountOperations.getAllByUserQuery(loggedUser!!)
                val simpleAccountsList = mutableListOf<BankAccountSimplified>()
                accountsList.forEach {
                    simpleAccountsList.add(
                        BankAccountSimplified(
                            it.number, it.balance
                        )
                    )
                }

                val jsonList = gson.toJson(simpleAccountsList)

                val response = Response(
                    success = true,
                    operation = "GETBANKACCOUNTS",
                    data = jsonList
                )
                val jsonResponse = gson.toJson(response)

                communicator.sendData(jsonResponse)
            }

            "CREATEBANKACCOUNT" -> {
                if (loggedUser == null) {
                    sendErrorResponse("CREATEBANKACCOUNT", "You are not logged in")
                    return
                }

                val userId = request["userId"].toString().toInt()
                if  (userId == 0) {
                    sendErrorResponse("CREATEBANKACCOUNT", "Internal error. Client.loggedClient is null")
                    return
                }

                val userEntity = UserOperations.get(userId)
                if (userEntity == null) {
                    sendErrorResponse("CREATEBANKACCOUNT", "Internal error. Cannot find user with id: $userId")
                    return
                }

                val bankAccount = BankAccountOperations.create(userEntity)
                if (bankAccount == null) {
                    sendErrorResponse("CREATEBANKACCOUNT", "Internal error. Cannot create bank account")
                    return
                }
                val bankAccountSimplified = BankAccountSimplified(
                    number = bankAccount.number,
                    balance = bankAccount.balance
                )
                val jsonBankAccountSimplified = gson.toJson(bankAccountSimplified)

                val response = Response(
                    success = true,
                    operation = "CREATEBANKACCOUNT",
                    data = jsonBankAccountSimplified
                )
                val jsonResponse = gson.toJson(response)

                communicator.sendData(jsonResponse)
            }

            "DELETEBANKACCOUNT" -> {
                if (loggedUser == null) {
                    sendErrorResponse("DELETEBANKACCOUNT", "You are not logged in")
                    return
                }

                val dataMap = Utils.getMapFromJson(data)
                val userId = dataMap["userId"].toString().toInt()
                val accountNumber = dataMap["number"].toString()

                if (userId == 0) {
                    sendErrorResponse("DELETEBANKACCOUNT", "Internal error. Client.loggedUser is null")
                    return
                }

                val userEntity = UserOperations.get(userId)
                if (userEntity == null) {
                    sendErrorResponse("DELETEBANKACCOUNT", "Internal error. Cannot find a user with id: $userId")
                    return
                }

                val bankAccountEntity = BankAccountOperations.getByUserAndNumber(userEntity, accountNumber)
                if (bankAccountEntity == null) {
                    sendErrorResponse("DELETEBANKACCOUNT", "Cannot find account of user ${userEntity.name} with number $accountNumber")
                    return
                }

                BankAccountOperations.delete(bankAccountEntity)
                val response = Response(
                    success = true,
                    operation = "DELETEBANKACCOUT",
                    data = "Successfully deleted bank account with number $accountNumber"
                )
                val jsonResponse = gson.toJson(response)

                communicator.sendData(jsonResponse)
            }

            "TRANSFER" -> {
                val transferService = TransferService(logger)
                val transferData = request["data"]

                if (transferData == null) {
                    sendErrorResponse("TRANSFER", "Bad request. Data is empty")
                    return
                }

                if (transferService.execute(transferData)) {
                    val response = Response(
                        success = true,
                        operation = "TRANSFER",
                        data = "Success transfer"
                    )
                    val jsonResponse = gson.toJson(response)

                    communicator.sendData(jsonResponse)
                } else {
                    val response = Response(
                        success = false,
                        operation = "TRANSFER",
                        data = "Error during transfer, check logs"
                    )
                    val jsonResponse = gson.toJson(response)

                    communicator.sendData(jsonResponse)
                }
            }
        }
    }

    private fun sendErrorResponse(operation: String, message: String) {
        val response = Response(
            success = false,
            operation = operation,
            data = message
        )
        val jsonResponse = gson.toJson(response)

        communicator.sendData(jsonResponse)
    }
}