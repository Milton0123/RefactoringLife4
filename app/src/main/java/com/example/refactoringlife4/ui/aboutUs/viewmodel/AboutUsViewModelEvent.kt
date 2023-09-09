package com.example.refactoringlife4.ui.aboutUs.viewmodel

sealed class AboutUsViewModelEvent {

    data class ShowSuccessView(val images: List<String>) : AboutUsViewModelEvent()
}
