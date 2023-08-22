package com.example.refactoringlife4.ui.splash.presenters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.refactoringlife4.ui.login.presenters.LoginActivity
import com.example.refactoringlife4.ui.onBoarding.presenters.OnBoardingActivity
import com.example.refactoringlife4.ui.splash.viewModel.SplashViewModel
import com.example.refactoringlife4.utils.CacheService

class SplashActivity : AppCompatActivity() {
    private var viewModel = SplashViewModel(CacheService(applicationContext))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
        checkUserSession()
        observe()
   }
        fun observe(){
            viewModel.getNameData.observe(this){
                when(it){
                    ""->{
                        goToLogin()
                    }else->{
                        goToOnBoarding()
                }
                }
            }
        }
        private fun checkUserSession() {
            viewModel.getUserState()
        }
        private fun goToLogin(){
            startActivity(Intent(this,LoginActivity::class.java))
        }
        private fun goToOnBoarding(){
            startActivity(Intent(this, OnBoardingActivity::class.java))
        }
}