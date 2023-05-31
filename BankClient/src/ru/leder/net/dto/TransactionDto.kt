package ru.leder.net.dto

import java.math.BigDecimal

data class TransactionDto (
    val initiatorBankAccountId : Int,
    val recipientBankAccountId: Int,
    val recipientBankAccountNumber: String?,
    val amount: BigDecimal
)