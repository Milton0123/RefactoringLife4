package com.example.refactoringlife4

import android.util.Patterns

object Utils {
    private fun checkEmail(email: String): Boolean {
        return email.isNotEmpty() && email.length <= 30 && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

    private fun checkPass(pass: String): Boolean {
        return pass.length in 1..16 && pass.all { it.isLetterOrDigit() } && !pass.contains(" ")
    }

    fun checkUser(email: String, pass: String): Boolean {
        return checkEmail(email) && checkPass(pass)
    }
}
