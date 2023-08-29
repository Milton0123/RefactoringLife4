package com.example.refactoringlife4.ui.home.presenter.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.dto.Result
import com.example.refactoringlife4.model.response.DogsResponse
import com.example.refactoringlife4.model.usesCases.DogsUseCase
import com.example.refactoringlife4.model.usesCases.UserUsesCase
import com.example.refactoringlife4.utils.CodesError.CODE_401
import com.example.refactoringlife4.utils.CodesError.CODE_500
import com.example.refactoringlife4.utils.CodesError.SUCCESS_200
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val context: Context,
    private val dogsUseCase: DogsUseCase = DogsUseCase(context = context),
    private val userUsesCase: UserUsesCase = UserUsesCase()
) : ViewModel() {

    private val _data = MutableLiveData<HomeViewModelEvent>()
    val data: LiveData<HomeViewModelEvent> = _data

    fun clearUserState() {
        CoroutineScope(Dispatchers.IO).launch {
            userUsesCase.clearUser.invoke()
        }
    }

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
