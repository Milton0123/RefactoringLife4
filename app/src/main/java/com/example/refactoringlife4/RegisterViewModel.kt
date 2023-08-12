package com.example.refactoringlife4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns

class RegisterViewModel : ViewModel() {
    private val _isRegistrationValid = MutableLiveData<Boolean>()
    val isRegistrationValid: LiveData<Boolean> = _isRegistrationValid

    fun validateFields(name: String, email: String, password: String) {
        val isNameValid = validateName(name)
        val isEmailValid = validateEmail(email)
        val isPasswordValid = validatePassword(password)
        _isRegistrationValid.value = isNameValid && isEmailValid && isPasswordValid
    }

    private fun validateName(name: String): Boolean {
        val nameWords = name.split("\\s+".toRegex())
        return name.isNotEmpty() && name.length in 3..30 && nameWords.size <= 2 && !name.contains("\\s{2,}".toRegex())
    }

    private fun validateEmail(email: String): Boolean {
        return email.isNotEmpty() && email.length <= 30 && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() && password.length in 6..30
    }
}
