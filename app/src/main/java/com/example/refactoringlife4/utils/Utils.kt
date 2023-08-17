package com.example.refactoringlife4.utils

object Utils {
    fun checkUserLogin(email: String, pass: String): Boolean {
        return email.verifyEmail() && pass.verifyPassword()
    }

    fun checkUserRegister(email: String, pass: String, name: String): Boolean {
        return email.verifyEmail() && pass.verifyPassword() && name.verifyName()
    }
}
