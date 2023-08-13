package com.example.refactoringlife4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginMailViewModel : ViewModel() {
    private val _isLoginValid = MutableLiveData<Boolean>()
    val isLoginValid: LiveData<Boolean> = _isLoginValid
    val checkUser = MutableLiveData<Boolean>()

    fun checkUserValidation(email: String, pass : String){
        checkUser.postValue(Utils.checkUser(email,pass))
    }


    private val responseData = MutableLiveData<String>()
    val responseLiveData: LiveData<String> = responseData

    fun status(status: FireBaseResponse.Status) {
        val responseText = when (status) {
            FireBaseResponse.Status.SUCCESS -> "SUCCESS"
            FireBaseResponse.Status.ERROR_PASSWORD -> "PASSWORD OR MAIL INCORRECT"
            FireBaseResponse.Status.EMAIL_DONT_EXIST -> "ERROR EMAIL DONT EXIST"
            FireBaseResponse.Status.ERROR -> "ERROR"
            else -> "ERROR"
        }
        responseData.value = responseText

    }
}
