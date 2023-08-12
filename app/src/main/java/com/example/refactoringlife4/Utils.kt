package com.example.refactoringlife4
import android.util.Patterns
object Utils {
    fun checkPassword(pass : String):AlertErrorField{
        return pass.length in 1..16 && pass.all {it.isLetterOrDigit()} && !pass.contains( " " )
    }

}