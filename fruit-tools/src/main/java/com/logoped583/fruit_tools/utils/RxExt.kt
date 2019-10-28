package com.logoped583.fruit_tools.utils

import androidx.room.EmptyResultSetException
import com.logoped583.fruit_tools.CustomExceptions
import com.logoped583.fruit_tools.ListResponse
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun <T> Single<T>.mapErrors(call: (error: CustomExceptions) -> Unit): Single<T> {
    return onErrorResumeNext { error ->
            Timber.i(" CHECKED EXCEPTION !!!! $error")
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
                is EmptyResultSetException -> {
                    CustomExceptions.ANOTHER("Data not stored in cache")
                }
                else -> CustomExceptions.ANOTHER(error.message ?: "")
            }
            call(customError)
            Single.error(customError)
        }
}


fun <T> Single<T>.retryIfTimeOut(): Single<T> {
    return retryWhen { errors ->
        errors.flatMap { error ->
            if (error is TimeoutException || error is InterruptedException) {
                Flowable.timer(3, TimeUnit.SECONDS).switchMapSingleDelayError {
                    this
                }
            } else {
                throw error
            }
        }
    }
}


fun <T : ListResponse<*>> callWhenInternetIsAvailable(
    internetStateSubject: BehaviorSubject<Boolean>,
    call: Single<T>
): Single<T> {
    return internetStateSubject
        .observeOn(Schedulers.io())
        .filter { it }
        .firstOrError()
        .flatMap { call }
        .retryWhen { errors ->
            errors.flatMap { error ->
                if (error !is UnknownHostException && error !is ConnectException) {
                    throw error
                } else {
                    Flowable.timer(3, TimeUnit.SECONDS)
                }
            }
        }
}
