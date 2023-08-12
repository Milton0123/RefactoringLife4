package com.example.refactoringlife4

import java.io.Serializable

data class FireBaseResponse<out T>(val status: Status, val data: T?, val message: String) :
    Serializable {

    companion object {

        fun <T> success(data: T? = null, message: String = ""): FireBaseResponse<T> {
            return FireBaseResponse(Status.SUCCESS, data, message)
        }

        fun <T> error(data: T? = null, message: String = ""): FireBaseResponse<T> {
            return FireBaseResponse(Status.ERROR_EMAIL_EXIST, data, message)
        }

        fun <T> errorExist(data: T? = null, message: String = ""): FireBaseResponse<T> {
            return FireBaseResponse(Status.ERROR_EMAIL_EXIST, data, message)
        }

        fun <T> errorPassword(data: T? = null, message: String = ""): FireBaseResponse<T> {
            return FireBaseResponse(Status.ERROR_EMAIL_EXIST, data, message)
        }

        fun <T> errorDontExist(data: T? = null, message: String = ""): FireBaseResponse<T> {
            return FireBaseResponse(Status.ERROR_EMAIL_EXIST, data, message)
        }

    }

    fun isSuccessful(): Boolean {
        return status == Status.SUCCESS
    }

    enum class Status {
        SUCCESS, ERROR, ERROR_EMAIL_EXIST, ERROR_PASSWORD, EMAIL_DONT_EXIST
    }
}
