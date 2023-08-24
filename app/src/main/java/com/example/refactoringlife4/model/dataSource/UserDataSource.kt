package com.example.refactoringlife4.model.dataSource

import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.dto.UserModelResponse
import com.example.refactoringlife4.model.service.UserFirebaseService

class UserDataSource(private val userFirebaseService: UserFirebaseService = UserFirebaseService()) {

    suspend fun userLogin(email: String, password: String): Result<UserModelResponse> {
        return userFirebaseService.login(email, password)
    }

    suspend fun userRegister(
        email: String,
        userName: String,
        password: String
    ): Result<UserModelResponse> {
        return userFirebaseService.register(email, userName, password)
    }

    suspend fun changeUser(email: String): Result<UserModelResponse>{
        return userFirebaseService.userUpdate(email)
    }
}
