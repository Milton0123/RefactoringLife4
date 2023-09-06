package com.example.refactoringlife4

import com.example.refactoringlife4.ui.home.presenter.viewmodel.HomeViewModelEvent

sealed class SearchViewModelEvent {

    data class ShowSuccessView(val image:List<String>) : SearchViewModelEvent()

    object ShowError: SearchViewModelEvent()
}