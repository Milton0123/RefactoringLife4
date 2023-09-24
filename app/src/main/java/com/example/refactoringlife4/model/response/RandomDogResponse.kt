package com.example.refactoringlife4.model.response

import com.google.gson.annotations.SerializedName

data class RandomDogResponse(
    @SerializedName("message") val image: String,
    @SerializedName("status") val status: String
)