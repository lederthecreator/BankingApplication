package ru.leder.net


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import ru.leder.net.entities.BankAccountSimplified
import ru.leder.net.entities.UserSimplified
import ru.leder.net.request.Request
import ru.leder.net.utils.Utils
import java.net.Socket

class Client(
    host: String,
    port: Int
) {
    private val socket: Socket
    private val communicator: Communicator
    private val mainCoroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private val gson = Gson()
    // private val mainWindow = MainWindow { sendRequest(it) }

    private var loggedUser: UserSimplified? = null
    init {
        socket = Socket(host, port)
        communicator = Communicator(socket)
    }

    fun start () = mainCoroutineScope.launch {
        launch {
           //  mainWindow.isVisible = true
            communicator.startReceiving { parseJsonResponse(it) }
        }

        launch {
            while(isActive)
            {
                println("Enter operation and values in format 'OPERATION value1|value2|...'")
                val input = readlnOrNull() ?: ""

                if (input.isEmpty()) {
                    println("Invalid input")
                    continue
                }

                sendRequest(input)
            }
        }
    }

    private fun sendRequest(input: String) {
        val request = parseInputString(input)
        val jsonRequest = gson.toJson(request)

        communicator.sendData(jsonRequest)
    }

    private fun parseInputString(input: String) : Request? {
        val parsedInput = input.split(" ", limit = 2)

        val operation = parsedInput[0]
        val arguments = if (parsedInput.size > 1) parsedInput[1] else ""
        val parsedArgs = arguments.split('|')

        when (operation) {
            "LOGIN" -> {
                return Request(
                    operation = operation,
                    data = object {
                        val login = parsedArgs[0]
                        val password = parsedArgs[1]
                    }
                )
            }

            "SIGNUP" -> {
                return Request(
                    operation = operation,
                    data = object {
                        val name = parsedArgs[0]
                        val login = parsedArgs[1]
                        val password = parsedArgs[2]
                    }
                )
            }

            "LOGOUT" -> {
                return Request(
                    operation = operation,
                    data = ""
                )
            }

            "GETBANKACCOUNTS" -> {
                return Request(
                    operation = operation,
                    data = ""
                )
            }

            "CREATEBANKACCOUNT" -> {
                return Request(
                    operation = operation,
                    data = object {
                        val userId = loggedUser?.id ?: 0
                    }
                )
            }

            "DELETEBANKACCOUNT" -> {
                return Request(
                    operation = operation,
                    data = object {
                        val userId = loggedUser?.id ?: 0
                        val number = parsedArgs[0]
                    }
                )
            }

            "TRANSFER" -> { // TRANSFER intitiator_bank_account|recipient_id|recipient_bank_account|amount
                return Request(
                    operation = operation,
                    data = object {
                        val initiator = loggedUser?.id ?: 0
                        val initiatorBankAccount = parsedArgs[0]
                        val recipient = parsedArgs[1].toInt()
                        val recipientBankAccount = parsedArgs[2]
                        val amount = parsedArgs[3].toBigDecimal()
                    }
                )
            }
        }

        return null
    }

    private fun parseJsonResponse(json: String) {
        val response = Utils.getMapFromJson(json)

        val success = response["success"].toString().toBoolean()
        val operation = response["operation"].toString()
        val data = response["data"].toString()

        if (!success) {
            if (operation == "LOG") {
                println(data)
                return
            }
            println(data)
            return
        }

        when (operation) {
            "LOGIN" -> {
                val user = gson.fromJson(data, UserSimplified::class.java)
                loggedUser = user
                println("Hello, ${user.name}!")

                // mainWindow.loginDtoReceiver(LoginDTO(user.name))
            }

            "SIGNUP" -> {
                val user = gson.fromJson(data, UserSimplified::class.java)
                loggedUser = user
                println("Successfully signed up! Welcome, ${user.name}!")
            }

            "LOGOUT" -> {
                val userName = loggedUser!!.name // В ином случае будет success == false
                loggedUser = null
                println("Successfully logged out! See you soon, $userName")
            }

            "GETBANKACCOUNTS" -> {
                val typeToken = object : TypeToken<List<BankAccountSimplified>>() {}.type
                val accountList = gson.fromJson<List<BankAccountSimplified>>(data, typeToken)

                if (accountList.isEmpty()) {
                    println("Cannot find any user's bank account")
                    return
                }

                accountList.forEach {
                    println("Bank account list for user ${loggedUser!!.name}") // Т.к. если loggedUser == null => success == false
                    println(it)
                }
            }

            "CREATEBANKACCOUNT" -> {
                val bankAccount = gson.fromJson(data, BankAccountSimplified::class.java)
                println("Successfully created bank account with number: ${bankAccount.number}")
            }
        }
    }
}