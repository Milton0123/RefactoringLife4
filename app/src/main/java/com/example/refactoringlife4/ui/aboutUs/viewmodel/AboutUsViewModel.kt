package com.example.refactoringlife4.ui.aboutUs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.usesCases.UsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutUsViewModel(private val usUseCase: UsUseCase = UsUseCase()): ViewModel() {
    private val _data = MutableLiveData<AboutUsViewModelEvent>()
    val data: LiveData<AboutUsViewModelEvent> = _data

    fun getUs(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = usUseCase.invokeUs()

            _data.postValue(AboutUsViewModelEvent.ShowSuccessView(response.images))
        }
    }
}