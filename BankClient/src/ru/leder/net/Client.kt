package ru.leder.net


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.leder.gui.ErrorWindow
import ru.leder.gui.MainWindow
import ru.leder.net.dto.BankAccountDto
import ru.leder.net.dto.UserDto
import ru.leder.net.entities.BankAccountSimplified
import ru.leder.net.entities.TransactionSimplified
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
    private val mainWindow : MainWindow

    private var loggedUser: UserSimplified? = null
    init {
        socket = Socket(host, port)
        communicator = Communicator(socket)
        mainWindow = MainWindow { sendRequest(it) }
    }

    fun start () = mainCoroutineScope.launch {
        launch {
            communicator.startReceiving { parseJsonResponse(it) }
        }

        launch {
//            while(isActive)
//            {
//                println("Enter operation and values in format 'OPERATION value1|value2|...'")
//                val input = readlnOrNull() ?: ""
//
//                if (input.isEmpty()) {
//                    println("Invalid input")
//                    continue
//                }
//
//                sendRequest(input)
//            }

            mainWindow.isVisible = true
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
                        val initiator = parsedArgs[0]
                        val initiatorBankAccount = parsedArgs[1]
                        val recipient = parsedArgs[2]
                        val recipientBankAccount = parsedArgs[3]
                        val amount = parsedArgs[4].toBigDecimal()
                        val type = parsedArgs[5]
                    }
                )
            }

            "GETBANKACCOUNT" -> {
                val id = parsedArgs[0].toInt()

                return Request(
                    operation = operation,
                    data = object {
                        val id = id
                    }
                )
            }

            "GETTRANSACTIONS" -> {
                return Request(
                    operation = operation,
                    data = object {
                        val bankAccountId = parsedArgs[0]
                    }
                )
            }

            "GETBANKACCOUNTSBYLOGIN" -> {
                return Request(
                    operation = operation,
                    data = object {
                        val userLogin = parsedArgs[0]
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
                val errorWindow = ErrorWindow(data)
                errorWindow.isVisible = true
                // println(data)
                return
            }

            // println(data)
            // val errorWindow = ErrorWindow(data)
            // errorWindow.isVisible = true

            return
        }

        when (operation) {
            "LOGIN", "SIGNUP" -> {
                val user = gson.fromJson(data, UserSimplified::class.java)
                loggedUser = user
                //println("Hello, ${user.name}!")

                val dto = UserDto (
                    name = user.name,
                    login = user.login,
                    id = user.id
                )

                mainWindow.userDtoReceiver(dto)
            }

            "LOGOUT" -> {
                val userName = loggedUser!!.name // В ином случае будет success == false
                loggedUser = null
                println("Successfully logged out! See you soon, $userName")
            }

            "GETBANKACCOUNTS" -> {
                val typeToken = object : TypeToken<List<BankAccountSimplified>>() {}.type
                val accountList = gson.fromJson<List<BankAccountSimplified>>(data, typeToken)

                /*
                if (accountList.isEmpty()) {
                println("Cannot find any user's bank account")
                return
                }

                accountList.forEach {
                println("Bank account list for user ${loggedUser!!.name}") // Т.к. если loggedUser == null => success == false
                println(it)
                }
                */

                mainWindow.bankAccountListReceiver(accountList)
            }

            "CREATEBANKACCOUNT" -> {
                val bankAccount = gson.fromJson(data, BankAccountSimplified::class.java)
                println("Successfully created bank account with number: ${bankAccount.number}")
            }

            "GETBANKACCOUNT" -> {
                val dto = gson.fromJson(data, BankAccountDto::class.java)
                mainWindow.bankAccountReceiver(dto)
            }

            "TRANSFER" -> {
                mainWindow.transferReceiver()
            }

            "GETTRANSACTIONS" -> {
                val typeToken = object : TypeToken<List<TransactionSimplified>>() {}.type
                val query = gson.fromJson<List<TransactionSimplified>>(data, typeToken)

                mainWindow.transactionsReceiver(query)
            }

            "GETBANKACCOUNTSBYLOGIN" -> {
                val typeToken = object : TypeToken<List<BankAccountSimplified>>() {}.type
                val accountList = gson.fromJson<List<BankAccountSimplified>>(data, typeToken)

                mainWindow.checkUserBankAccountsReceiver(accountList)
            }
        }
    }
}