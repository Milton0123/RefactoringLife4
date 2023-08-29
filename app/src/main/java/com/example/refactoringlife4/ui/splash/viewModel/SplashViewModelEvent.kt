package com.example.refactoringlife4.ui.splash.viewModel

sealed class SplashViewModelEvent {
    object ShowHome : SplashViewModelEvent()
    object ShowLogin : SplashViewModelEvent()
}
