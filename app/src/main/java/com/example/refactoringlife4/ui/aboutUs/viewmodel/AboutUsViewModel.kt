package com.example.refactoringlife4.ui.aboutUs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.usesCases.UsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AboutUsViewModel(private val usUseCase: UsUseCase = UsUseCase()) : ViewModel() {
    private val _dataUs = MutableLiveData<AboutUsViewModelEvent>()
    val dataUs: LiveData<AboutUsViewModelEvent> = _dataUs
    private val _dataMembers = MutableLiveData<AboutUsViewModelEvent>()
    val dataMembers: LiveData<AboutUsViewModelEvent> = _dataMembers


    fun getUs() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = usUseCase.invokeUs()

            _dataUs.postValue(AboutUsViewModelEvent.ShowSuccessView(response.images))
        }
    }

    fun getMembers() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = usUseCase.invokeMembers()

            _dataMembers.postValue(AboutUsViewModelEvent.ShowSuccessView(response.images))
        }
    }
}
