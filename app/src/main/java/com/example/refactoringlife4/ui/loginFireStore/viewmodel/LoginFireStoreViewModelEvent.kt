package com.example.refactoringlife4.ui.loginFireStore.viewmodel

import com.example.refactoringlife4.model.dto.UserModel

sealed class LoginFireStoreViewModelEvent {

    data class ShowModalError(
        val modalDialog: UserModel.ModalDialog
    ) : LoginFireStoreViewModelEvent()

    object ShowOnBoarding : LoginFireStoreViewModelEvent()

    object ShowHome : LoginFireStoreViewModelEvent()
}
