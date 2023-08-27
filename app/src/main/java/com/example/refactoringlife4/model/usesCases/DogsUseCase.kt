package com.example.refactoringlife4.model.usesCases

import com.example.refactoringlife4.model.dto.DogsModel
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.mapper.toDogsModel
import com.example.refactoringlife4.model.repository.DogsRepository
import com.example.refactoringlife4.model.repository.UserRepository
import com.example.refactoringlife4.model.response.DogsResponse

class DogsUseCase(private val dogsRepository: DogsRepository = DogsRepository()) {

    suspend fun invoke(): DogsModel? {
        val call = dogsRepository.getDogs()
        return when (call.status) {
            Result.Status.SUCCESS -> {
                call.toDogsModel(true)
            }
            else -> {
                call.toDogsModel(false)
            }
        }
    }
}
