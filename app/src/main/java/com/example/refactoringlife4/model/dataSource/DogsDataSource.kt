package com.example.refactoringlife4.model.dataSource

import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.response.DogsResponse
import com.example.refactoringlife4.model.service.DogServiceImp

class DogsDataSource(private val dogService: DogServiceImp = DogServiceImp()) {

    suspend fun getDogs(): Result<DogsResponse> {
        return dogService.getDogs()
    }
}
