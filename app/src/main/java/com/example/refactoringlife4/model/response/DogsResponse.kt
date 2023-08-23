package com.example.refactoringlife4.model.response

import com.google.gson.annotations.SerializedName

data class DogsResponse(
    @SerializedName("message") val dog:String,
    @SerializedName("success") val status :String
)