package com.example.refactoringlife4.model.dataSource

import android.content.Context
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.response.DogsResponse
import com.example.refactoringlife4.model.response.RandomDogResponse
import com.example.refactoringlife4.model.response.OneDogResponse
import com.example.refactoringlife4.model.service.DogServiceImp
import com.example.refactoringlife4.utils.Utils.isInternetAvailable

class DogsDataSource(
    private val dogService: DogServiceImp = DogServiceImp(),
    private val context: Context
) {

    suspend fun getDogs(): Result<DogsResponse> {
        return if (isInternetAvailable(context)) {
            dogService.getDogs()
        } else {
            Result.error(null, "", Result.Status.ERROR_LOST_CONNECTION)
        }
    }

    suspend fun getRandomDog(): Result<RandomDogResponse> {
        return if (isInternetAvailable(context)) {
            dogService.getRandomDog()
        } else {
            Result.error(null, "", Result.Status.ERROR_LOST_CONNECTION)
        }
    }

    suspend fun getOneDog(nameBreed: String): Result<OneDogResponse> {
        return if (isInternetAvailable(context)) {
            dogService.getOneDogForBreed(nameBreed)
        } else {
            Result.error(null, "", Result.Status.ERROR_LOST_CONNECTION)
        }
    }
}
