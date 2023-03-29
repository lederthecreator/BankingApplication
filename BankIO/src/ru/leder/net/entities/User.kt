package ru.leder.net.entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Users : IntIdTable() {
    val name : Column<String> = varchar("name", 200)

    val login : Column<String> = varchar("login", 40)

    val password : Column<String> = varchar("password", 40)
}

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    var name by Users.name

    var login by Users.login

    var password by Users.password

    override fun toString(): String {
        return "$name | $login | $password"
    }
}
data class UserSimplified(val id: Int, val login: String, val name: String)


