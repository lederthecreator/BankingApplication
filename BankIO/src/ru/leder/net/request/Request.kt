package ru.leder.net.request

import java.io.Serializable;

data class Request(override val operation: String,
                   override val data: Any) : IRequest, Serializable