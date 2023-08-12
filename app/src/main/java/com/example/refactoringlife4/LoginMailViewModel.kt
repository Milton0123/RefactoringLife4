package com.example.refactoringlife4

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginMailViewModel() : ViewModel() {
    private val userData = MutableLiveData<AlertErrorField>()

    fun checkLoginEmail(email: String) {
        userData.postValue(checkEmail(email))
    }

    fun checkLoginPass(pass: String) {
        userData.postValue(Utils.checkPassword(pass))
    }

    fun checkEmail(email : String):Boolean{
        return if(Patterns.EMAIL_ADDRESS.matcher(email).matches() && !email.contains( " " )){
            userData.postValue(AlertErrorField.SUCCESS)
            true
        }else{
            userData.postValue(AlertErrorField.ERROR_EMAIL)
            false
        }

    }
}
