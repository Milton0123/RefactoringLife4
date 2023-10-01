package com.example.refactoringlife4.ui.adoptDog.presenters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.refactoringlife4.databinding.ActivityAdoptDogBinding
import com.example.refactoringlife4.ui.home.presenter.HomeActivity
import com.example.refactoringlife4.utils.Utils
import com.squareup.picasso.Picasso

class AdoptDogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdoptDogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdoptDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageUrl = intent.getStringExtra("imageUrl")

        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(binding.ivAdoptDog)
        }

        onClick()
    }

    private fun onClick() {
        binding.btAdoptDog.setOnClickListener {
            Utils.startActivityWithSlideToRight(this, HomeActivity::class.java, null)
        }
    }
}
