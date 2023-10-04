package com.example.refactoringlife4.model.dto

import java.io.Serializable

data class Result<out T>(val status: Status, val data: T?, val message: String) :
    Serializable {

    companion object {

        fun <T> success(data: T? = null, message: String = ""): Result<T> {
            return Result(Status.SUCCESS, data, message)
        }

        fun <T> error(data: T? = null, message: String = "", status: Status): Result<T> {
            return Result(status, data, message)
        }

        fun <T> errorCode(data: T? = null, message: String = ""): Result<T> {
            return Result(Status.ERROR_CODE, data, message)
        }
    }

    fun isSuccessful(): Boolean {
        return status == Status.SUCCESS
    }

    enum class Status {
        SUCCESS, ERROR, ERROR_EMAIL_EXIST, ERROR_PASSWORD, EMAIL_DONT_EXIST, ERROR_LOST_CONNECTION,
        ERROR_CODE
    }
}
