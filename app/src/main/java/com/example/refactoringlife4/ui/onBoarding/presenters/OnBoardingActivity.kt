package com.example.refactoringlife4.ui.onBoarding.presenters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import com.example.refactoringlife4.R
import com.example.refactoringlife4.databinding.ActivityOnboardingBinding
import com.example.refactoringlife4.ui.home.presenter.HomeActivity
import com.example.refactoringlife4.ui.login.LoginActivity
import com.example.refactoringlife4.ui.onBoarding.viewmodel.OnBoardingViewModel
import com.example.refactoringlife4.ui.onBoarding.viewmodel.OnBoardingViewModelEvent
import com.example.refactoringlife4.ui.onBoarding.viewmodel.OnBoardingViewModelFactory
import com.example.refactoringlife4.utils.Utils


class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewModel: OnBoardingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        val email = extras?.getString("email")
        Log.i("MiTag", email.toString())
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moveArrowAnimation = AnimationUtils.loadAnimation(this, R.anim.mov_arrow)
        binding.onboardArrow.startAnimation(moveArrowAnimation)
        action(email = email!!)
        getViewModel()
        observer()
    }

    private fun observer() {
        viewModel.data.observe(this) {
            when (it) {
                is OnBoardingViewModelEvent.changeUser -> {
                    goHome()
                }
            }
        }
    }

    private fun action(email: String) {
        binding.onboardFootprintBack.setOnClickListener {
            viewModel.changeUser( email = email)
        }
    }

    private fun goHome() {
        Utils.startActivityWithSlideToLeft(this, HomeActivity::class.java, null)
    }

    private fun goToBack() {
        Utils.startActivityWithSlideToRight(this, LoginActivity::class.java, null)
        finish()
    }

    private fun getViewModel() {
        viewModel = OnBoardingViewModelFactory().create(OnBoardingViewModel::class.java)
    }

    override fun onBackPressed() {
        goToBack()
    }
}
