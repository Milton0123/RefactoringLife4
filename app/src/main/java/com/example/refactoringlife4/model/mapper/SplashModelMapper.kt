package com.example.refactoringlife4.model.mapper

import com.example.refactoringlife4.model.dto.UserMailModel

fun String.toUserModelMail(state: Boolean): UserMailModel {
    return UserMailModel(this, state)
}
