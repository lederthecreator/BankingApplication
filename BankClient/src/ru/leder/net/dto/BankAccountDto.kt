package ru.leder.net.dto

import java.math.BigDecimal

data class BankAccountDto (
    val id: Int,
    val number: String,
    val balance: BigDecimal,
    val type: String?,
    val currency: String?,
    val duration: String?
)