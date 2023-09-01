package com.example.refactoringlife4.model.usesCases

import android.content.Context

class UserUsesCase {
    val register = RegisterUseCase()
    val login = LoginUseCase()
    val getLocalUseCase = GetLocalUseCase()
    val loginLocalUseCase = LoginLocalUseCase()
    val changeUser = ChangeUserUseCase()
    val clearUser = LogOutLocalUseCase()
}
