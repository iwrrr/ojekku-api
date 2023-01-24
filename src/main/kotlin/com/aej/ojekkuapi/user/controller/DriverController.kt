package com.aej.ojekkuapi.user.controller

import com.aej.ojekkuapi.user.entity.LoginResponse
import com.aej.ojekkuapi.user.entity.User
import com.aej.ojekkuapi.user.entity.UserLogin
import com.aej.ojekkuapi.user.entity.request.DriverRegisterRequest
import com.aej.ojekkuapi.user.services.UserServices
import com.aej.ojekkuapi.utils.BaseResponse
import com.aej.ojekkuapi.utils.extensions.toResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user/driver")
class DriverController {

    @Autowired
    private lateinit var userServices: UserServices

    @PostMapping("/register")
    fun register(
        @RequestBody userRequest: DriverRegisterRequest
    ): BaseResponse<Boolean> {
        val user = userRequest.mapToUser()
        return userServices.register(user).toResponse()
    }
}