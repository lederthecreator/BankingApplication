package ru.leder.net

import kotlinx.coroutines.*
import java.net.ServerSocket

class Server(val port: Int = 5004) {
    private val serverSocket = ServerSocket(port)
    private val mainCoroutineScope = CoroutineScope(Dispatchers.IO + Job())
    private val dbContext = DbContext()

    fun start () = mainCoroutineScope.launch {
        while (isActive) {
            try {
                serverSocket.accept().apply {
                    ConnectedClient(this).apply {
                        launch {
                            try {
                                start()
                            } catch (ex: Throwable) {
                                throw ex
                            }
                        }
                    }
                }
            } catch (ex: Throwable)
            {
                println(ex.localizedMessage)
            }
        }
    }

    fun stop(){
        mainCoroutineScope.cancel("Stopped by user")
        serverSocket.close()
    }

}