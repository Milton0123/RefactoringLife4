package com.example.refactoringlife4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginMailViewModel() : ViewModel() {
    val passData = MutableLiveData<Boolean>()
    val emailData = MutableLiveData<Boolean>()

    fun checkLoginEmail(email: String) {
        emailData.postValue(Utils.checkEmail(email))
    }

    fun checkLoginPass(pass: String) {
        passData.postValue(Utils.checkPassword(pass))
    }
}
