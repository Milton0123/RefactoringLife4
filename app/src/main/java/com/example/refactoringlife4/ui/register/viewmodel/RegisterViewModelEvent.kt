package com.example.refactoringlife4.ui.register.viewmodel

import com.example.refactoringlife4.model.dto.LoginModel

sealed class RegisterViewModelEvent {

    object ShowSuccessView : RegisterViewModelEvent()

    data class ShowModalError(
        val title: String,
        val description: String,
        val firstButtonLabel: String,
        val secondButtonLabel: String
    ) : RegisterViewModelEvent()
}