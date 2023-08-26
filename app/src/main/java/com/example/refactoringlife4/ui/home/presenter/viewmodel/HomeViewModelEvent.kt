package com.example.refactoringlife4.ui.home.presenter.viewmodel

sealed class HomeViewModelEvent {

    data class ShowSuccessView(val images:List<String>) : HomeViewModelEvent()

    data class ShowError(
        val message: String
    ) : HomeViewModelEvent()
}
