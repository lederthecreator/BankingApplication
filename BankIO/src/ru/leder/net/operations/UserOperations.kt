package ru.leder.net.operations

import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import ru.leder.net.entities.User
import ru.leder.net.entities.Users

object UserOperations {
    fun getAllQuery(): List<User> = User.all().sortedByDescending { it.id }

    fun get(id: Int) : User? {
        var entity: User? = null

        transaction {
            addLogger(StdOutSqlLogger)

            entity = User.find {
                Users.id eq id
            }.firstOrNull()
        }

        return entity
    }


    fun update (user: User) : User {
        val entity = User[user.id]

        if (entity.name != user.name) {
            entity.name = user.name
        }

        return entity

    }

    fun delete (user: User) {
        transaction {
            user.delete()
        }
    }

    fun getByLogin(login: String): User? {
        var user: User? = null

        transaction {
           user = User.find {
               Users.login eq login
           }.maxByOrNull { it.id }
        }

        return user
    }
}