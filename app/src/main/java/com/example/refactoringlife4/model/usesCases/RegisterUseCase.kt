package com.example.refactoringlife4.model.usesCases

import com.example.refactoringlife4.model.dto.UserModel
import com.example.refactoringlife4.model.mapper.toUserModel
import com.example.refactoringlife4.model.repository.UserRepository

class RegisterUseCase(private val userRepository: UserRepository = UserRepository()) {

    suspend fun invoke(email:String, userName:String, password:String):UserModel{
        val call = userRepository.userRegister(email,userName,password)
        return call.toUserModel()
    }
}
