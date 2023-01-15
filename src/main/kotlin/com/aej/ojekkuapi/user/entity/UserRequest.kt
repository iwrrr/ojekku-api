package com.aej.ojekkuapi.user.entity

data class UserRequest(
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var phoneNumber: String = "",
) {

    fun mapToNewUser(): User {
        return User.createNewUser(
            username = username,
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName,
            address = address,
            phoneNumber = phoneNumber
        )
    }

    fun mapToNewCustomer(): User {
        return User.createNewCustomer(
            username = username,
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName,
            address = address,
            phoneNumber = phoneNumber
        )
    }

    fun mapToNewDriver(): User {
        return User.createNewDriver(
            username = username,
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName,
            address = address,
            phoneNumber = phoneNumber
        )
    }
}