package com.example.refactoringlife4.model.mapper

import com.example.refactoringlife4.model.dto.DogsModel
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.dto.UserModel
import com.example.refactoringlife4.model.response.DogsResponse

fun Result<DogsResponse>.toDogsModel(state: Boolean): DogsModel {
    return DogsModel(this.data?.image ?: emptyList(), state)
}
