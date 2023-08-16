package com.example.refactoringlife4.model.usesCases

import com.example.refactoringlife4.model.dto.LoginModel
import com.example.refactoringlife4.model.mapper.toLoginModel
import com.example.refactoringlife4.model.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository = UserRepository()) {

    suspend fun invoke (email:String, password:String): LoginModel {
        val call = userRepository.userLogin(email,password)
        return call.toLoginModel()
    }
}