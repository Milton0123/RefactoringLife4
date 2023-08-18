package com.example.refactoringlife4.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.refactoringlife4.R

object Utils {
    fun checkUserLogin(email: String, pass: String): Boolean {
        return email.verifyEmail() && pass.verifyPassword()
    }

    fun checkUserRegister(email: String, pass: String, name: String): Boolean {
        return email.verifyEmail() && pass.verifyPassword() && name.verifyName()
    }
    fun  startActivityWithSlideToLeft(context: Context, destinationActivity: Class<*>) {
        val intent = Intent(context, destinationActivity)
        context.startActivity(intent)
        (context as? Activity)?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
    fun  startActivityWithSlideToRight(context: Context, destinationActivity: Class<*>) {
        val intent = Intent(context, destinationActivity)
        context.startActivity(intent)
        (context as? Activity)?.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}
