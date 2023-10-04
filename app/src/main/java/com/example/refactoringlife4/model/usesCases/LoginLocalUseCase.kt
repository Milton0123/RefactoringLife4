package com.example.refactoringlife4.model.usesCases

import com.example.refactoringlife4.model.repository.UserRepository

class LoginLocalUseCase(private val userRepository: UserRepository = UserRepository()) {
    suspend fun invoke(user: String) {
        userRepository.loadUserCache(user)
    }
}
