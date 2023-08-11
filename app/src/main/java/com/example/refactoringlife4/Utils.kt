package com.example.refactoringlife4
import android.util.Patterns
object Utils {
    fun checkPassword(pass : String):Boolean{
        return pass.length in 1..16 && pass.all {it.isLetterOrDigit()} && !pass.contains( " " )
    }
    fun checkEmail(email : String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && !email.contains( " " )
    }
}