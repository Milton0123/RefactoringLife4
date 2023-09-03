package com.example.refactoringlife4.ui.aboutUs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AboutUsViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return(AboutUsViewModel()) as T
    }
}