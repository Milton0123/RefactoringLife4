package com.example.refactoringlife4.model.service

import com.example.refactoringlife4.model.response.DogsResponse
import retrofit2.http.GET
import com.example.refactoringlife4.model.dto.Result

interface DogService {

    @GET("list/all")
    suspend fun getDogs():Result<DogsResponse>
}