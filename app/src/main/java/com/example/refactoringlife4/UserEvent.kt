package com.example.refactoringlife4

sealed class UserEvent {

    object ShowSuccessView : UserEvent()

    data class ShowModalError(
        val title: String,
        val description: String,
        val firstButtonLabel: String,
        val secondButtonLabel: String
    ) : UserEvent()
}
