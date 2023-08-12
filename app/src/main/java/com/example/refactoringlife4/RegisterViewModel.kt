package com.example.refactoringlife4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns

class RegisterViewModel : ViewModel() {
    private val _isRegistrationValid = MutableLiveData<Boolean>()
    val isRegistrationValid: LiveData<Boolean> = _isRegistrationValid

    private val responseData = MutableLiveData<String>()
    val responseLiveData: LiveData<String> = responseData

    fun status(status: FireBaseResponse.Status) {
        val responseText = when (status) {
            FireBaseResponse.Status.SUCCESS -> "SUCCESS"
            FireBaseResponse.Status.ERROR_EMAIL_EXIST -> "ERROR EMAIL EXIST"
            FireBaseResponse.Status.ERROR -> "ERROR"
            else -> "ERROR"
        }
        responseData.value = responseText

    }

    fun validateFields(name: String, email: String, password: String) {
        val isNameValid = validateName(name)
        val isEmailValid = validateEmail(email)
        val isPasswordValid = validatePassword(password)
        _isRegistrationValid.value = isNameValid && isEmailValid && isPasswordValid
    }


    private fun String.isValidName(): Boolean {
        val nameWords = split("\\s+".toRegex())
        return isNotEmpty() && length in 3..30 && nameWords.size <= 2 && !contains("\\s{2,}".toRegex())
    }

    private fun String.isValidEmail(): Boolean {
        return isNotEmpty() && length <= 30 && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun String.isValidPassword(): Boolean {
        return isNotEmpty() && length in 6..30

    }

    private fun validateName(name: String): Boolean {
        val nameWords = name.split("\\s+".toRegex())
        return name.isNotEmpty() && name.length in 3..30 && nameWords.size <= 2 && !name.contains(
            "\\s{2,}".toRegex()
        )
    }

    private fun validateEmail(email: String): Boolean {
        return email.isNotEmpty() && email.length <= 30 && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() && password.length in 6..30

    }

}
