package com.example.refactoringlife4.model.repository

import android.util.Log
import com.example.refactoringlife4.model.dataSource.UserDataSource
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.dto.UserModelResponse

class UserRepository(private val userDataSource: UserDataSource = UserDataSource()) {

    suspend fun userLogin(email: String, password: String): Result<UserModelResponse> {

        val result = userDataSource.userLogin(email,password)
        Log.i("loginResult",result.status.name)
      return result
    }

    suspend fun userRegister(email: String, userName:String, password: String): Result<UserModelResponse> {
        return userDataSource.userRegister(email,userName,password)
    }
}
