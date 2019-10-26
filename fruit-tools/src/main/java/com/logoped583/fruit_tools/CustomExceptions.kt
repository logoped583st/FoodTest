package com.logoped583.fruit_tools

sealed class CustomExceptions(override val message: String) : Exception() {
    object ServerError : CustomExceptions("SERVER DIED")
    object SocketTimeOut : CustomExceptions("TIMEOUT")
    data class ANOTHER(override val message: String) : CustomExceptions(message)
}