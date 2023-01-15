package com.aej.ojekkuapi.utils

import com.aej.ojekkuapi.exception.OjekuException

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