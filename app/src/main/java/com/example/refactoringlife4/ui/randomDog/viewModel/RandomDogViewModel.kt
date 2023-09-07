package com.example.refactoringlife4.ui.randomDog.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.usesCases.RandomDogUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomDogViewModel(
    context: Context,
    private val randomDogUseCase: RandomDogUseCase = RandomDogUseCase(context)
) : ViewModel() {

    private val _data = MutableLiveData<RandomDogViewModelEvent>()
    val data: LiveData<RandomDogViewModelEvent> = _data

    fun getRandomDog() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = randomDogUseCase.invoke()
            if (response.isSuccessful) {
                _data.postValue(RandomDogViewModelEvent.ShowSuccessView(response.images))
            } else {
                _data.postValue(RandomDogViewModelEvent.ShowError)
            }
        }
    }
}
