package com.example.refactoringlife4.ui.register.viewmodel

import com.example.refactoringlife4.model.dto.UserModel

sealed class RegisterViewModelEvent {

    object ShowSuccessView : RegisterViewModelEvent()

    data class ShowModalError(
        val modalDialog: UserModel.ModalDialog
    ) : RegisterViewModelEvent()
}
