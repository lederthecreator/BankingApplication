package ru.leder.net

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.isActive
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Communicator (val socket: Socket) {
    suspend fun startReceiving(dataAnalyser: (String) -> Unit) {
        val br = BufferedReader(InputStreamReader(socket.getInputStream()))
        coroutineScope {
            while (isActive)
            {
                val data = br.readLine()
                if (data.isNotEmpty())
                {
                    val a = 2 + 2
                }
                dataAnalyser(data)
            }
        }
    }

    fun sendData(data: String) {
        val pw = PrintWriter(socket.getOutputStream())
        pw.println(data)
        pw.flush()
    }
}