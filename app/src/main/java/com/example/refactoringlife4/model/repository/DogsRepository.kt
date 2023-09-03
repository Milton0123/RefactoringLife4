package com.example.refactoringlife4.model.repository

import android.content.Context
import com.example.refactoringlife4.model.dataSource.DogsDataSource
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.response.DogsResponse
import com.example.refactoringlife4.model.response.RandomDogResponse
import retrofit2.Response

class DogsRepository(private val context: Context,private val dogsDataSource: DogsDataSource = DogsDataSource(context= context) ) {

    suspend fun getDogs(): Result<DogsResponse> {
        return dogsDataSource.getDogs()
    }

    suspend fun getRandomDog(): Result<RandomDogResponse> {
        return dogsDataSource.getRandomDog()
    }
}
