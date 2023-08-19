package com.example.refactoringlife4.ui.onBoarding.presenters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.refactoringlife4.R
import com.example.refactoringlife4.ui.login.LoginActivity
import com.example.refactoringlife4.utils.Utils


class OnBoardingActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
    }
    private fun goToBack() {
        Utils.startActivityWithSlideToRight(this, LoginActivity::class.java, null)
        finish()
    }

    override fun onBackPressed() {
        goToBack()
    }
}
