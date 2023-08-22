package com.example.refactoringlife4.ui.splash.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.utils.CacheService

class SplashViewModel(val data : CacheService):ViewModel(){
    val getNameData = MutableLiveData<String>()

    fun getUserState(){
        getNameData.postValue(data.getNameUser())
    }

}