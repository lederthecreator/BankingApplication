package ru.leder.net.request

interface IRequest {
    val operation: String

    val data: Any
}