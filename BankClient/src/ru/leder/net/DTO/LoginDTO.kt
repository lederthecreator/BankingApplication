package ru.leder.net.DTO

import ru.leder.net.entities.BankAccountSimplified

data class LoginDTO(
    val name: String,
    val bankAccountList: List<BankAccountSimplified>
)