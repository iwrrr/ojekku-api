package com.aej.ojekkuapi.user.entity

import com.aej.ojekkuapi.location.entity.Coordinate
import com.aej.ojekkuapi.user.entity.extra.DriverExtras
import com.aej.ojekkuapi.user.entity.extra.emptyExtra
import java.util.*

data class User(
    var id: String = "",
    var username: String = "",
    var password: String? = "",
    var role: Role = Role.CUSTOMER,
    var extra: Any = emptyExtra(),
    var coordinate: Coordinate = Coordinate()
) {

    companion object {
        fun createNewDriver(username: String, password: String, extra: DriverExtras): User {
            return User(
                id = UUID.randomUUID().toString(),
                username = username,
                password = password,
                role = Role.DRIVER,
                extra = extra
            )
        }

        fun createNewCustomer(username: String, password: String): User {
            return User(
                id = UUID.randomUUID().toString(),
                username = username,
                password = password,
                role = Role.CUSTOMER
            )
        }
    }

    enum class Role {
        CUSTOMER, DRIVER
    }
}
