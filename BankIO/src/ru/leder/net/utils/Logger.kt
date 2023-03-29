package ru.leder.net.utils

import com.google.gson.Gson
import ru.leder.net.Communicator
import ru.leder.net.response.Response
import java.util.*


class Logger(val cmn: Communicator) {
    @Synchronized
    fun log(message: String)
    {
        val gson = Gson()
        val response = Response (
            success = false,
            operation = "LOG",
            data = "[${Date()}] $message"
        )
        val jsonResponse = gson.toJson(response)
        cmn.sendData(jsonResponse)
    }
}