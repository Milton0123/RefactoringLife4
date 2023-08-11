package com.example.refactoringlife4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns

class RegisterViewModel : ViewModel() {

    private val _isRegistrationValid = MutableLiveData<Boolean>()
    val isRegistrationValid: LiveData<Boolean> = _isRegistrationValid

    fun validateFields(name: String, email: String, password: String) {
        val isNameValid = name.isValidName()
        val isEmailValid = email.isValidEmail()
        val isPasswordValid = password.isValidPassword()

        _isRegistrationValid.value = isNameValid && isEmailValid && isPasswordValid
    }
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
