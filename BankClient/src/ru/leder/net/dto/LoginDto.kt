package ru.leder.net.dto

import ru.leder.net.entities.BankAccountSimplified

data class LoginDto(
    val name: String,
    val bankAccountList: List<BankAccountSimplified>
)