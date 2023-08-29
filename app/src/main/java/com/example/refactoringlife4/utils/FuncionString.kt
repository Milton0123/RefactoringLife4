package com.example.refactoringlife4.utils

import android.util.Patterns
import com.example.refactoringlife4.model.dto.UserMailModel

fun String.verifyEmail(): Boolean {
    return this.isNotEmpty() && this.length <= 30 && Patterns.EMAIL_ADDRESS.matcher(this)
        .matches() && !this.contains(" ")
}

fun String.verifyName(): Boolean {
    val nameWords = this.split("\\s+".toRegex())
    return this.isNotEmpty() && this.length in 1..30 && nameWords.size <= 2 && !this.contains(
        "\\s{2,}".toRegex()
    ) && !this.contains(" ")
}

fun String.verifyPassword(): Boolean {
    return this.length in 6..30 && this.all { it.isLetterOrDigit() } && !this.contains(" ")
}

