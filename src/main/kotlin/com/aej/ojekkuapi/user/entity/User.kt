package com.aej.ojekkuapi.user.entity

import com.aej.ojekkuapi.utils.Role
import java.util.*

data class User(
    var id: String = "",
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var phoneNumber: String = "",
    var role: Int = 0,
) {

    companion object {
        fun createNewUser(
            username: String,
            email: String,
            password: String,
            firstName: String,
            lastName: String,
            address: String,
            phoneNumber: String,
        ): User {
            return User(
                id = UUID.randomUUID().toString(),
                username = username,
                email = email,
                password = password,
                firstName = firstName,
                lastName = lastName,
                address = address,
                phoneNumber = phoneNumber
            )
        }

        fun createNewCustomer(
            username: String,
            email: String,
            password: String,
            firstName: String,
            lastName: String,
            address: String,
            phoneNumber: String,
        ): User {
            return User(
                id = UUID.randomUUID().toString(),
                username = username,
                email = email,
                password = password,
                firstName = firstName,
                lastName = lastName,
                address = address,
                phoneNumber = phoneNumber,
                role = Role.CUSTOMER.id
            )
        }

        fun createNewDriver(
            username: String,
            email: String,
            password: String,
            firstName: String,
            lastName: String,
            address: String,
            phoneNumber: String,
        ): User {
            return User(
                id = UUID.randomUUID().toString(),
                username = username,
                email = email,
                password = password,
                firstName = firstName,
                lastName = lastName,
                address = address,
                phoneNumber = phoneNumber,
                role = Role.DRIVER.id
            )
        }
    }
}
