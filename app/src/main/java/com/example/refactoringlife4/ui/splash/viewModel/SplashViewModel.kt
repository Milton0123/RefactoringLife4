package com.example.refactoringlife4.ui.splash.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.usesCases.UserUsesCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(private val userUsesCase: UserUsesCase = UserUsesCase()) : ViewModel() {
    var data = MutableLiveData<SplashViewModelEvent>()

    fun getUserState() {
        CoroutineScope(Dispatchers.IO).launch {
            val userCache = userUsesCase.getLocalUseCase.invoke()
            if (userCache.mailState) {
                Log.i("userState", userCache.mailState.toString())
                data.postValue(SplashViewModelEvent.ShowHome)
            } else {
                Log.i("userState", userCache.mailState.toString())
                data.postValue(SplashViewModelEvent.ShowLogin)
            }
        }
    }

}
