package com.example.refactoringlife4.ui.randomDog.presents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import com.example.refactoringlife4.databinding.ActivityRandomDogBinding
import com.example.refactoringlife4.ui.randomDog.viewModel.RandomDogViewModel
import com.example.refactoringlife4.ui.randomDog.viewModel.RandomDogViewModelEvent
import com.example.refactoringlife4.ui.randomDog.viewModel.RandomDogViewModelFactory

class RandomDogActivity : AppCompatActivity() {

    private lateinit var viewModel: RandomDogViewModel
    private lateinit var binding: ActivityRandomDogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRandomDogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observer()
        getViewModel()
        actions()
        onclick()
    }

    private fun observer() {
        viewModel.data.observe(this) {
            when (it) {

                is RandomDogViewModelEvent.ShowSuccessView -> {
                    it.image
                    binding.errorView.root.visibility = View.GONE
                    binding.loadingView.root.visibility = View.GONE
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
    }

    private fun showLoading() {
        binding.loadingView.root.visibility = View.VISIBLE
        binding.errorView.root.visibility = View.GONE
    }

    private fun showError() {
        actions()
        binding.errorView.root.visibility = View.VISIBLE
        binding.loadingView.root.visibility = View.GONE
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
                    binding.btBackBlackTermsAndConditions.isEnabled = true
                }
            }
            binding.btBackWhiteTermsAndConditions.isEnabled = false
        }
    }
}
