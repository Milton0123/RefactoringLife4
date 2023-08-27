package com.example.refactoringlife4.ui.home.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.response.DogsResponse
import com.example.refactoringlife4.model.usesCases.DogsUseCase
import com.example.refactoringlife4.utils.CodesError.CODE_401
import com.example.refactoringlife4.utils.CodesError.CODE_500
import com.example.refactoringlife4.utils.CodesError.SUCCESS_200
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val dogsUseCase: DogsUseCase = DogsUseCase()) : ViewModel() {

    private val _data = MutableLiveData<HomeViewModelEvent>()
    val data: LiveData<HomeViewModelEvent> = _data

    fun getDogs() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = dogsUseCase.invoke()

            response?.let{
                if(it.isSuccessful){
                    _data.postValue(HomeViewModelEvent.ShowSuccessView(it.images))
                } else {
                    _data.postValue(HomeViewModelEvent.ShowError(it.toString()))
                }
            }
        }
    }
}
