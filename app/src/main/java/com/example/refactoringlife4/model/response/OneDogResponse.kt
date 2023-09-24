package com.example.refactoringlife4.model.response

import com.google.gson.annotations.SerializedName

class OneDogResponse(
    @SerializedName("message") val image: String,
    @SerializedName("status") val status: String
)
