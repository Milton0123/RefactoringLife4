package com.example.refactoringlife4.ui.all_dog.viewmodel

sealed class AllDogsViewModelEvent {
    data class ShowSuccessView(val images: List<String>) : AllDogsViewModelEvent()
    object ShowError : AllDogsViewModelEvent()
}
