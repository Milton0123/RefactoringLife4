package com.example.refactoringlife4.ui.all_dog.presenters

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import com.example.refactoringlife4.databinding.ActivityAllDogBinding
import com.example.refactoringlife4.ui.all_dog.adapter.AllDogAdapter
import com.example.refactoringlife4.ui.all_dog.viewmodel.AllDogsViewModel
import com.example.refactoringlife4.ui.all_dog.viewmodel.AllDogsViewModelEvent
import com.example.refactoringlife4.ui.all_dog.viewmodel.AllDogsViewModelFactory
import com.example.refactoringlife4.ui.home.presenter.HomeActivity
import com.example.refactoringlife4.utils.Utils

class AllDogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllDogBinding
    private lateinit var viewModel: AllDogsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getInitViewModel()
        getRandomDogs()
        onClicks()
        observe()
    }

    private fun getRandomDogs() {
        viewModel.getRandomDogsList()
    }

    private fun getInitViewModel() {
        viewModel = AllDogsViewModelFactory(applicationContext).create(AllDogsViewModel::class.java)
    }

    private fun onClicks() {
        binding.allDogFootprint.setOnClickListener {
            animateFootprint()
            Handler().postDelayed({
                recreate()
            }, 1000)
        }
        binding.btBackBlackTermsAndConditions.setOnClickListener {
            binding.btBackBlackTermsAndConditions.isEnabled = false

            binding.btBackBlackTermsAndConditions.animate().apply {
                translationX(300f)
                interpolator = AccelerateDecelerateInterpolator()
                duration = 1000
                withEndAction {
                    goToHome()
                    finish()
                    binding.btBackBlackTermsAndConditions.isEnabled = true
                }
            }
            binding.btBackWhiteTermsAndConditions.isEnabled = false
        }
    }

    private fun goToHome(){
        Utils.startActivityWithSlideToRight(this, HomeActivity::class.java, null)
    }
    private fun goToBack() {
        Utils.startActivityWithSlideToRight(this, HomeActivity::class.java, null)
    }

    private fun observe() {
        viewModel.data.observe(this) {
            when (it) {
                is AllDogsViewModelEvent.ShowSuccessView -> {
                    initRecyclerView(it.images)
                }
                is AllDogsViewModelEvent.ShowError -> {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initRecyclerView(listRandomDogs: List<String>) {
        val adapter = AllDogAdapter(listRandomDogs)
        binding.allDogRvRandomDogs.adapter = adapter
    }

    private fun animateFootprint() {
        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            binding.allDogFootprint.rotationY = animatedValue
        }
        animator.duration = 1000
        animator.start()
    }

    override fun onBackPressed() {
        goToBack()
    }

}
