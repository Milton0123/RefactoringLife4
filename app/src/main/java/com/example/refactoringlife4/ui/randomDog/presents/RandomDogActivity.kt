package com.example.refactoringlife4.ui.randomDog.presents

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import com.example.refactoringlife4.databinding.ActivityRandomDogBinding
import com.example.refactoringlife4.ui.randomDog.viewModel.RandomDogViewModel
import com.example.refactoringlife4.ui.randomDog.viewModel.RandomDogViewModelEvent
import com.example.refactoringlife4.ui.randomDog.viewModel.RandomDogViewModelFactory
import com.squareup.picasso.Picasso

class RandomDogActivity : AppCompatActivity() {

    private lateinit var viewModel: RandomDogViewModel
    private lateinit var binding: ActivityRandomDogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRandomDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getViewModel()
        observer()
        actions()
        onclick()
    }

    private fun observer() {
        viewModel.data.observe(this) {
            when (it) {

                is RandomDogViewModelEvent.ShowSuccessView -> {
                    Picasso.get().load(it.image).into(binding.incImage.ivPhotoDog)
                    binding.incError.root.visibility = View.GONE
                    binding.incLoading.root.visibility = View.GONE
                }
                is RandomDogViewModelEvent.ShowError -> {
                    showError()
                }
                else -> {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun actions() {
        showLoading()
        viewModel.getRandomDog()

        binding.imChange.setOnClickListener {
            animateFootprint()
            showLoading()
            viewModel.getRandomDog()
        }
    }

    private fun showLoading() {
        binding.incLoading.root.visibility = View.VISIBLE
        binding.incError.root.visibility = View.GONE
    }

    private fun showError() {
        actions()
        binding.incError.root.visibility = View.VISIBLE
        binding.incLoading.root.visibility = View.GONE
    }

    private fun getViewModel() {
        viewModel = RandomDogViewModelFactory(this).create(RandomDogViewModel::class.java)
    }

    private fun onclick() {
        binding.btBackWhiteTermsAndConditions.isEnabled = false

        binding.btBackBlackTermsAndConditions.setOnClickListener {
            binding.btBackBlackTermsAndConditions.isEnabled = false

            binding.btBackBlackTermsAndConditions.animate().apply {
                translationX(300f)
                interpolator = AccelerateDecelerateInterpolator()
                duration = 1000
                withEndAction {
                    finish()
                    binding.btBackBlackTermsAndConditions.isEnabled = true
                }
            }
            binding.btBackWhiteTermsAndConditions.isEnabled = false
        }
    }

    private fun animateFootprint() {
        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            binding.imChange.rotationY = animatedValue
        }
        animator.duration = 1000
        animator.start()
    }
}
