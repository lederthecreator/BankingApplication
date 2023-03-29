import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.leder.net.request.Request

fun main (args: Array<String>) {
    val json = """{"title": "Kotlin Tutorial", "author": "bezkoder", "categories" : ["Kotlin","Basic"]}"""
    val request = Request(
        operation = "LOGIN",
        data = object {
            val login = "123"
            val pass = "123"
        }
    )
    val gson = Gson()

    val jsonRequest = gson.toJson(request)

    val mapType = object : TypeToken<Map<String, Any>>() {}.type

    val tutorialMap: Map<String, Any> = gson.fromJson(jsonRequest, object : TypeToken<Map<String, Any>>() {}.type)
    tutorialMap.forEach { println("Key: ${it.key} Value: ${it.value}") }
    val dataFromJson = tutorialMap["data"]
    println("data from json: $dataFromJson")
    val jsonData = gson.toJson(dataFromJson)

    println("--------------------------")
    val parsedData: Map<String, Any> = gson.fromJson(jsonData, object: TypeToken<Map<String, Any>>() {}.type)
    parsedData.forEach { println("Key: ${it.key} Value: ${it.value}") }
    val login = parsedData["login"].toString()
    println("login: $login")
}