package com.example.refactoringlife4.ui.loginFireStore.viewmodel

import com.example.refactoringlife4.model.dto.LoginModel

sealed class LoginViewModelEvent {

    object ShowSuccessView : LoginViewModelEvent()

    data class ShowModalError(
        val modalDialog: LoginModel.ModalDialog
    ) : LoginViewModelEvent()
}
