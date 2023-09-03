package com.example.refactoringlife4.ui.aboutUs.viewmodel

import com.example.refactoringlife4.ui.home.presenter.viewmodel.HomeViewModelEvent

sealed class AboutUsViewModelEvent {

    data class ShowSuccessView(val images:List<String>) : AboutUsViewModelEvent()
}