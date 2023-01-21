package com.aej.ojekkuapi.user.controller

import com.aej.ojekkuapi.user.entity.LoginResponse
import com.aej.ojekkuapi.user.entity.User
import com.aej.ojekkuapi.user.entity.UserLogin
import com.aej.ojekkuapi.user.entity.request.CustomerRegisterRequest
import com.aej.ojekkuapi.user.services.UserServices
import com.aej.ojekkuapi.utils.BaseResponse
import com.aej.ojekkuapi.utils.toResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/customer")
class CustomerController {

    @Autowired
    private lateinit var userServices: UserServices

    @GetMapping
    fun getCustomer(): BaseResponse<User> {
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        return userServices.getUserByUserId(userId).toResponse()
    }

    @PostMapping("/login")
    fun login(
        @RequestBody userLogin: UserLogin
    ): BaseResponse<LoginResponse> {
        return userServices.login(userLogin).toResponse()
    }

    @PostMapping("/register")
    fun register(
        @RequestBody userRequest: CustomerRegisterRequest
    ): BaseResponse<Boolean> {
        val user = userRequest.mapToUser()
        return userServices.register(user).toResponse()
    }
}