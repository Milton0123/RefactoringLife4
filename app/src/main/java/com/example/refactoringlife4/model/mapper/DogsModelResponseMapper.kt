package com.example.refactoringlife4.model.mapper

import com.example.refactoringlife4.model.dto.DogsModel
import com.example.refactoringlife4.model.dto.RandomDogModel
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.response.DogsResponse
import com.example.refactoringlife4.model.response.RandomDogResponse

fun Result<DogsResponse>.toDogsModel(state: Boolean): DogsModel {
    return DogsModel(this.data?.image ?: emptyList(), state)
}

fun Result<RandomDogResponse>.toDogRandomModel(state: Boolean): RandomDogModel {
    return RandomDogModel((this.data?.image ?: String) as String, state)
}
