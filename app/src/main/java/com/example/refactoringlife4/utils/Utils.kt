package com.example.refactoringlife4.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatButton
import com.example.refactoringlife4.R
import com.example.refactoringlife4.databinding.FragmentHomeBinding

object Utils {
    fun checkUserLogin(email: String, pass: String): Boolean {
        return email.verifyEmail() && pass.verifyPassword()
    }

    fun checkUserRegister(email: String, pass: String, name: String): Boolean {
        return email.verifyEmail() && pass.verifyPassword() && name.verifyName()
    }

    fun startActivityWithSlideToLeft(
        context: Context,
        destinationActivity: Class<*>,
        extras: Bundle? = null
    ) {
        val intent = Intent(context, destinationActivity)
        extras?.let {
            intent.putExtras(extras)
        }
        context.startActivity(intent)
        (context as? Activity)?.overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
    }

    fun startActivityWithSlideToRight(
        context: Context,
        destinationActivity: Class<*>,
        extras: Bundle? = null
    ) {
        val intent = Intent(context, destinationActivity)
        extras?.let {
            intent.putExtras(extras)
        }
        context.startActivity(intent)
        (context as? Activity)?.overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        }

        return false
    }

}
