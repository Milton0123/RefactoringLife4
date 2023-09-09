package com.example.refactoringlife4.ui.searchDog.viewModel

sealed class SearchViewModelEvent {

    data class ShowSuccessView(val image:String) : SearchViewModelEvent()

    object ShowError: SearchViewModelEvent()
}