package com.example.refactoringlife4.model.repository

import android.util.Log
import com.example.refactoringlife4.model.dataSource.UserDataSource
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.dto.UserModelResponse

class UserRepository(private val userDataSource: UserDataSource = UserDataSource()) {

    suspend fun userLogin(email: String, password: String): Result<UserModelResponse> {
        return userDataSource.userLogin(email, password)
    }

    suspend fun userRegister(
        email: String,
        userName: String,
        password: String
    ): Result<UserModelResponse> {
        val result = userDataSource.userRegister(email, userName, password)
        Log.i("registerResult", result.status.name)
        return result
    }

    fun loadUserCache(user: String) {
        userDataSource.loadUserServiceCache(user)
    }

    fun downloadUserCache(): String {
        return userDataSource.getUserServiceCache()
    }
    suspend fun changeUser(email: String): Result<UserModelResponse> {
        return userDataSource.changeUser(email = email)
    }

}
