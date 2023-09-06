package com.example.refactoringlife4.model.mapper

import com.example.refactoringlife4.model.dto.OneDogModel
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.response.DogsResponse
import com.example.refactoringlife4.model.response.OneDogResponse


fun Result<OneDogResponse>.toOneDogModel(state: Boolean): OneDogModel {
    return OneDogModel((this.data?.image ?: emptyList()), state)
}

