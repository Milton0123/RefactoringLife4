package com.example.refactoringlife4.model.usesCases

import android.util.Log
import com.example.refactoringlife4.model.dto.UserMailModel
import com.example.refactoringlife4.model.mapper.toUserModelMail
import com.example.refactoringlife4.model.repository.UserRepository

class GetLocalUseCase(private val userRepository: UserRepository = UserRepository()) {

    suspend fun invoke(): UserMailModel {
        return when (val userCache = userRepository.downloadUserCache()) {
            "" -> {
                Log.i("userStateEmpty", userCache)
                userCache.toUserModelMail(false)
            }
            else -> {
                Log.i("userStateIsNotEmpty", userCache)
                userCache.toUserModelMail(true)
            }
        }
    }
}
