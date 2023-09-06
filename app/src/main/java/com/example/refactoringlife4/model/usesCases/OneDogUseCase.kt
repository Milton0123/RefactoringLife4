package com.example.refactoringlife4.model.usesCases
import android.content.Context
import com.example.refactoringlife4.model.dto.OneDogModel
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.mapper.toOneDogModel
import com.example.refactoringlife4.model.repository.DogsRepository


class OneDogUseCase(private val context: Context, private val dogsRepository: DogsRepository = DogsRepository(context = context)) {

    suspend fun invoke(breed:String): OneDogModel {
        val call = dogsRepository.getOneDog(breed)
        return when (call.status) {
            Result.Status.SUCCESS -> {
                call.toOneDogModel(true)
            }
            else -> {
                call.toOneDogModel(false)
            }
        }
    }
}