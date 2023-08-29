package com.example.refactoringlife4.model.response

import com.google.gson.annotations.SerializedName

data class DogsResponse(
    @SerializedName("message") val image:List<String>,
    @SerializedName("status") val status :String
)