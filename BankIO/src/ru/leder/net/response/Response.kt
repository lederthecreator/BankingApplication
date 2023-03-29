package ru.leder.net.response


data class Response (
    override val success: Boolean,

    override val operation: String,

    override val data: String
) : IResponse