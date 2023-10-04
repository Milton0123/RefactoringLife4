package com.example.refactoringlife4.ui.splash.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SplashViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel() as T
    }

}
