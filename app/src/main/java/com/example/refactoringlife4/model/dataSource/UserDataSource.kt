package com.example.refactoringlife4.model.dataSource

import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.dto.UserModelResponse
import com.example.refactoringlife4.model.response.DogsResponse
import com.example.refactoringlife4.model.service.DogService
import com.example.refactoringlife4.model.service.UserFirebaseService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDataSource(private val userFirebaseService: UserFirebaseService = UserFirebaseService()) {

    private val url = "https://dog.ceo/api/breeds/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val serviceImp = retrofit.create(DogService::class.java)

    suspend fun getDogs(): Result<DogsResponse> {
        return serviceImp.getDogs()
    }

    suspend fun userLogin(email: String, password: String): Result<UserModelResponse> {
        return userFirebaseService.login(email, password)
    }

    suspend fun userRegister(
        email: String,
        userName: String,
        password: String
    ): Result<UserModelResponse> {
        return userFirebaseService.register(email, userName, password)
    }
}
