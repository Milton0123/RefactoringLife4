package com.example.refactoringlife4.ui.splash.presenters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.refactoringlife4.ui.home.presenter.HomeActivity
import com.example.refactoringlife4.ui.login.presenters.LoginActivity
import com.example.refactoringlife4.ui.splash.viewModel.SplashViewModel
import com.example.refactoringlife4.ui.splash.viewModel.SplashViewModelEvent
import com.example.refactoringlife4.ui.splash.viewModel.SplashViewModelFactory


class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        screenSplash.setKeepOnScreenCondition { true }
        super.onCreate(savedInstanceState)
        getViewModel()
        checkUserSession()
        observe()
    }

    private fun getViewModel() {
        viewModel = SplashViewModelFactory().create(SplashViewModel::class.java)
    }

    private fun checkUserSession() {
        viewModel.getUserState()
    }

    private fun observe() {
        Log.i("userStateObserve", "active")
        viewModel.data.observe(this) {
            Log.i("userStateResponseGetData", "live data funcionando")
            when (it) {
                is SplashViewModelEvent.ShowHome -> {
                    Log.i("userStateIt", "goHome")
                    goToHome()
                }
                is SplashViewModelEvent.ShowLogin -> {
                    Log.i("userStateIt", "goLogin")
                    goToLogin()
                }
            }
        }
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

}
