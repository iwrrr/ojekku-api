package com.aej.ojekkuapi.user.repository

import com.aej.ojekkuapi.database.DatabaseComponent
import com.aej.ojekkuapi.exception.OjekuException
import com.aej.ojekkuapi.user.entity.User
import com.aej.ojekkuapi.user.entity.extra.CustomerExtras
import com.aej.ojekkuapi.user.entity.extra.DriverExtras
import com.aej.ojekkuapi.utils.safeClassTo
import com.aej.ojekkuapi.utils.toResult
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    @Autowired
    private val databaseComponent: DatabaseComponent
) : UserRepository {

    private fun getCollection(): MongoCollection<User> {
        return databaseComponent.database.getDatabase("ojekku").getCollection()
    }

    override fun insertUser(user: User): Result<Boolean> {
        val existingUser = getUserByUsername(user.username)
        return if (existingUser.isSuccess) {
            throw OjekuException("User already exists!")
        } else {
            getCollection().insertOne(user).wasAcknowledged().toResult()
        }
    }

    override fun getUserById(id: String): Result<User> {
        return getCollection().findOne(User::id eq id).run {
            if (this?.role == User.Role.DRIVER) {
                this.extra.safeClassTo(DriverExtras::class.java)
            } else {
                this?.extra?.safeClassTo(CustomerExtras::class.java)
            }
            this
        }.toResult()
    }

    override fun getUserByUsername(username: String): Result<User> {
        return getCollection().findOne(User::username eq username).toResult("User not found!")
    }
}