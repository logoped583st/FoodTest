package com.logoped583.fruit_tools.utils

import com.logoped583.fruit_tools.CustomExceptions
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

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


fun <T> Single<T>.retryIfTimeOut(): Single<T> {
    return flatMap { this }
        .retryWhen { errors ->
            errors.flatMap { error ->
                if (error !is TimeoutException) {
                    throw error
                } else {
                    Flowable.timer(5, TimeUnit.SECONDS)
                }
            }
        }
}