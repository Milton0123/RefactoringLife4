package com.example.refactoringlife4.ui.congratulations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.refactoringlife4.databinding.ActivityCongratulationsBinding
import com.example.refactoringlife4.ui.onBoarding.presenters.OnBoardingActivity
import com.example.refactoringlife4.utils.Utils

class CongratulationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCongratulationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCongratulationsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val extras = intent.extras
        val email = extras?.getString("email")
        Log.i("MiTag", email.toString())
        goToOnboard(email)
    }

    private fun goToOnboard(email: String?) {
        binding.root.setOnClickListener {
            val extras = Bundle()
            extras.putString("email", email.toString())
            Utils.startActivityWithSlideToLeft(this, OnBoardingActivity::class.java, extras)
        }
    }
}
