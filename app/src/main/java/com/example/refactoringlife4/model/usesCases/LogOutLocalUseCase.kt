package com.example.refactoringlife4.model.usesCases

import com.example.refactoringlife4.model.repository.UserRepository

class LogOutLocalUseCase(private val userRepository: UserRepository = UserRepository()) {
    suspend fun invoke() {
        userRepository.clearUserCache()
    }
}