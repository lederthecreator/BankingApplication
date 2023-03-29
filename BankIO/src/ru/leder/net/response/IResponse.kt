package ru.leder.net.response

interface IResponse {
    val operation: String

    val success: Boolean

    val data: String
}