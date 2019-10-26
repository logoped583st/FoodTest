package com.logoped583.fruit_tools.utils

import com.logoped583.fruit_tools.CustomExceptions
import io.reactivex.Single
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun <T> Single<T>.mapErrors(call: (error: CustomExceptions) -> Unit): Single<T> {

    return onErrorResumeNext { error ->
        val customError: CustomExceptions = when (error) {
            is SocketTimeoutException -> {
                CustomExceptions.SocketTimeOut
            }
            is HttpException -> {
                when (error.code()) {
                    in 500..600 -> CustomExceptions.ServerError
                    else -> CustomExceptions.ANOTHER(error.message())
                }
            }
            else -> CustomExceptions.ANOTHER(error.message ?: "")
        }

        call(customError)


        return@onErrorResumeNext Single.error<T>(customError)
    }
}