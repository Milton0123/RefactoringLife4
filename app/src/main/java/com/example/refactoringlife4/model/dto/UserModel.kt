package com.example.refactoringlife4.model.dto

data class UserModel(
    val status: Result.Status,
    val modalDialog: ModalDialog?,
    val newUser: Boolean
) {
    data class ModalDialog(
        val title: String,
        val description: String,
        val firstAction: String,
        val secondAction: String
    )
}
