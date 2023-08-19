package com.example.refactoringlife4.ui.onBoarding.presenters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.refactoringlife4.R
import com.example.refactoringlife4.databinding.ActivityOnboardingBinding
import com.example.refactoringlife4.ui.login.LoginActivity
import com.example.refactoringlife4.utils.Utils


class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moveArrowAnimation = AnimationUtils.loadAnimation(this, R.anim.mov_arrow)
        binding.onboardArrow.startAnimation(moveArrowAnimation)
    }

    private fun goToBack() {
        Utils.startActivityWithSlideToRight(this, LoginActivity::class.java, null)
        finish()
    }

    override fun onBackPressed() {
        goToBack()
    }
}
