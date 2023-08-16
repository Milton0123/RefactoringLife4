package com.example.refactoringlife4.model.dto

data class LoginModel(
    val status: Result.Status,
    val modalDialog: ModalDialog?
) {
    data class ModalDialog(
        val title: String,
        val description: String,
        val firstAction: String,
        val secondAction: String
    )
}