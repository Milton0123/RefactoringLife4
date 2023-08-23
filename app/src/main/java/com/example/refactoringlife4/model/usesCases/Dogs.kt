package com.example.refactoringlife4.model.usesCases

import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.repository.UserRepository
import com.example.refactoringlife4.model.response.DogsResponse

class Dogs(private val userRepository: UserRepository = UserRepository()) {

    suspend fun invoke (): Result<DogsResponse> {
        return userRepository.getDogs()
    }
}