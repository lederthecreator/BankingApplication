import kotlinx.coroutines.runBlocking
import ru.leder.net.Server

fun main () = runBlocking {
    Server().start().join()
}