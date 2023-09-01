package com.example.refactoringlife4.ui.home.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.usesCases.DogsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val context: Context,
    private val dogsUseCase: DogsUseCase = DogsUseCase(context = context)
) : ViewModel() {

    private val _data = MutableLiveData<HomeViewModelEvent>()
    val data: LiveData<HomeViewModelEvent> = _data

    fun getDogs() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = dogsUseCase.invoke()

            if (response.isSuccessful) {
                _data.postValue(HomeViewModelEvent.ShowSuccessView(response.images))
            } else {
                _data.postValue(HomeViewModelEvent.ShowError)
            }
        }
    }
}
