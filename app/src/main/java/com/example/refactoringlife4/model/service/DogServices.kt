package com.example.refactoringlife4.model.service

import com.example.refactoringlife4.model.response.DogsResponse
import com.example.refactoringlife4.model.response.RandomDogResponse
import com.example.refactoringlife4.model.response.OneDogResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path

interface DogServices {

    //all dog
    @GET("breeds/image/random/50")
    suspend fun getDogs(): Response<DogsResponse>

    //random dog
    @GET("breeds/image/random")
    suspend fun getRandomDog():Response<RandomDogResponse>

    //get one dog
    @GET("breed/{african}/images/random")
    suspend fun getOneDog(@Path("african") id: String): Response<OneDogResponse>
}
