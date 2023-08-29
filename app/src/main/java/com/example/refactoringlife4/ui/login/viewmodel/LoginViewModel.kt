package com.example.refactoringlife4.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.usesCases.UserUsesCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: UserUsesCase = UserUsesCase()) : ViewModel() {

    fun loadCache(user: String) {
        CoroutineScope(Dispatchers.IO).launch {
            useCase.loginLocalUseCase.invoke(user)
        }
    }

}
