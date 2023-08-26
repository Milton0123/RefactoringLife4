package com.example.refactoringlife4.model.repository

import com.example.refactoringlife4.model.dataSource.DogsDataSource
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.response.DogsResponse
import retrofit2.Response

class DogsRepository(private val dogsDataSource: DogsDataSource = DogsDataSource()) {

    suspend fun getDogs(): Result<DogsResponse> {
        return dogsDataSource.getDogs()
    }
}
