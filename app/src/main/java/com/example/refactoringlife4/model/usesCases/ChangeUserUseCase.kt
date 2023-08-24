package com.example.refactoringlife4.model.usesCases

import com.example.refactoringlife4.model.dto.UserModel
import com.example.refactoringlife4.model.mapper.toUserModel
import com.example.refactoringlife4.model.repository.UserRepository

class ChangeUserUseCase(private val userRepository: UserRepository = UserRepository()) {

    suspend fun invoke(email: String): UserModel {
        val call = userRepository.changeUser(email = email)
        return call.toUserModel()
    }
}
