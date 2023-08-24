package com.example.refactoringlife4.ui.onBoarding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.usesCases.UserUsesCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnBoardingViewModel(private val userUsesCase: UserUsesCase = UserUsesCase()) :
    ViewModel() {

    private val _data = MutableLiveData<OnBoardingViewModelEvent>()
    val data: LiveData<OnBoardingViewModelEvent> = _data

    fun changeUser(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = userUsesCase.changeUser.invoke(email = email)
            when (response.newUser) {
                true -> _data.postValue(OnBoardingViewModelEvent.changeUser)
                false -> _data.postValue(OnBoardingViewModelEvent.changeUser)
            }

        }
    }
}
