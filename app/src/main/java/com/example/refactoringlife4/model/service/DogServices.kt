package com.example.refactoringlife4.model.service

import com.example.refactoringlife4.model.response.DogsResponse
import retrofit2.http.GET
import retrofit2.Response

interface DogServices {

    @GET("breeds/image/random/50")
    suspend fun getDogs(): Response<DogsResponse>
}
