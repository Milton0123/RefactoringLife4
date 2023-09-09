package com.example.refactoringlife4.ui.all_dog.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.usesCases.DogsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllDogsViewModel(
    context: Context,
    private val dogsUseCase: DogsUseCase = DogsUseCase(context = context)
) : ViewModel() {
    val data = MutableLiveData<AllDogsViewModelEvent>()
    fun getRandomDogsList() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = dogsUseCase.invoke()
            if (response.isSuccessful) {
                data.postValue(AllDogsViewModelEvent.ShowSuccessView(response.images))
            } else {
                data.postValue(AllDogsViewModelEvent.ShowError)
            }

        }
    }

}
