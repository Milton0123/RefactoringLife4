package com.example.refactoringlife4

import java.io.Serializable

data class FireBaseResponse<out T>(val status: Status, val data: T?, val message: String) :
    Serializable {

    companion object {

        fun <T> success(data: T? = null, message: String = ""): FireBaseResponse<T> {
            return FireBaseResponse(Status.SUCCESS, data, message)
        }

        fun <T> error(data: T? = null, message: String = ""): FireBaseResponse<T> {
            return FireBaseResponse(Status.ERROR_EMAIL, data, message)
        }

    }

    fun isSuccessful(): Boolean {
        return status == Status.SUCCESS
    }

    enum class Status {
        SUCCESS, ERROR_EMAIL, ERROR_PASSWORD, ERROR_USER
    }
}
