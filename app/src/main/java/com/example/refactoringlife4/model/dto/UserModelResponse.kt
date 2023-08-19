package com.example.refactoringlife4.model.dto


data class UserModelResponse(
    val name: String,
    val password: String,
    val mail: String,
    val type: String,
    val modelDialog: ModalDialog?
) {
    data class ModalDialog(
        val title: String,
        val description: String,
        val firstAction: String,
        val secondAction: String
    )
}
