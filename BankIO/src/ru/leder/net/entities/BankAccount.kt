package ru.leder.net.entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import java.math.BigDecimal

object BankAccounts : IntIdTable() {
    val number : Column<String> = varchar("number", 20)

    val balance = decimal("balance", 10, 3)

    val userId = reference("user_id", Users.id, onDelete = ReferenceOption.CASCADE)

    val productId = reference("product_id", Products.id, onDelete = ReferenceOption.SET_NULL).nullable()
}

class BankAccount(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BankAccount>(BankAccounts)

    var number by BankAccounts.number

    var balance by BankAccounts.balance

    var userId by User referencedOn BankAccounts.userId

    var productId by Product optionalReferencedOn  BankAccounts.productId

    override fun toString(): String {
        return "$number $balance ${userId.name}"
    }
}

data class BankAccountSimplified(val id: Int, val number: String, val balance: BigDecimal){
    override fun toString(): String {
        return "$number $balance"
    }
}