package com.example.refactoringlife4.ui.home.presenter.viewmodel

sealed class HomeViewModelEvent {

    data class ShowSuccessView(val images:List<String>) : HomeViewModelEvent()

    object ShowError: HomeViewModelEvent()
}