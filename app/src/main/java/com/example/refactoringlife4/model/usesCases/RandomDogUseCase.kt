package com.example.refactoringlife4.model.usesCases

import android.content.Context
import com.example.refactoringlife4.model.dto.RandomDogModel
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.mapper.toDogRandomModel
import com.example.refactoringlife4.model.repository.DogsRepository

class RandomDogUseCase(
    private val context: Context,
    private val dogsRepository: DogsRepository = DogsRepository(context = context)) {

    suspend fun invoke(): RandomDogModel {
        val call = dogsRepository.getRandomDog()
        return when (call.status) {
            Result.Status.SUCCESS -> {
                call.toDogRandomModel(true)
            }
            else -> {
                call.toDogRandomModel(false)
            }
        }
    }
}
