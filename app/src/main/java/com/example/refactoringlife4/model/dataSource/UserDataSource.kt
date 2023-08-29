package com.example.refactoringlife4.model.dataSource

import android.util.Log
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.dto.UserModelResponse
import com.example.refactoringlife4.model.service.UserFirebaseService
import com.example.refactoringlife4.utils.AppInstanceCache.Companion.getInstance

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

    //registerUserCache pasamanos que la funcipon pida el context por parametros

    fun loadUserServiceCache(user: String) {
        val cacheService = getInstance
        Log.i("userStateData", user)
        cacheService.setNameUser(user)
    }

    fun getUserServiceCache(): String {
        val cacheService = getInstance//reemplazar por el objeto chache
        return cacheService.getNameUser().toString()
    }
}
