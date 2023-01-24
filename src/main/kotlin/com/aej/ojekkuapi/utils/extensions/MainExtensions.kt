package com.aej.ojekkuapi.utils.extensions

import com.aej.ojekkuapi.exception.OjekuException
import com.aej.ojekkuapi.location.entity.Coordinate
import com.aej.ojekkuapi.utils.BaseResponse
import org.springframework.security.core.context.SecurityContextHolder

inline fun <reified T> T?.orThrow(
    message: String = "${T::class.simpleName} is null"
): T {
    return this ?: throw OjekuException(message)
}

inline fun <reified T> T?.toResult(
    message: String = "${T::class.simpleName} is null"
): Result<T> {
    return if (this != null) {
        Result.success(this)
    } else {
        Result.failure(OjekuException(message))
    }
}

fun <T> Result<T>.toResponse(): BaseResponse<T> {
    return if (this.isFailure) {
        throw OjekuException(this.exceptionOrNull()?.message ?: "Failure")
    } else {
        BaseResponse.success(this.getOrNull())
    }
}

fun String.coordinateStringToData(): Coordinate {
    val coordinateStrings = split(",")
    val lat = coordinateStrings[0].toDoubleOrNull() ?: 0.0
    val lng = coordinateStrings[1].toDoubleOrNull() ?: 0.0
    return Coordinate(lat, lng)
}

fun Any.findUserId(): String? = SecurityContextHolder.getContext().authentication.principal as? String