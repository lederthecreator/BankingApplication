package ru.leder.net.entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import ru.leder.net.enums.CurrencyType

object Products : IntIdTable() {
    val currency = enumeration("currency_type", CurrencyType::class)

    val name = varchar("name", 200)

    val duration = timestamp("duration")
}

class Product(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Product>(Products)

    var currency by Products.currency

    var name by Products.name

    var duration by Products.duration
}