package com.example.refactoringlife4.ui.details.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import com.example.refactoringlife4.R
import com.example.refactoringlife4.databinding.ActivityDetailsBinding
import com.example.refactoringlife4.ui.adoptDog.presenters.AdoptDogActivity
import com.example.refactoringlife4.utils.Utils
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var currentDescription: String = ""
    private var selectedButtonId: Int = R.id.cv_details_circle_dog_food
    private var imageUrl : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCircleButtonListeners()

        // Establece el primer botón como seleccionado al iniciar
        selectButton(selectedButtonId)

        // Obtener la URL de la imagen del Intent
        imageUrl = intent.getStringExtra("imageUrl").toString()

        // Verificar si la URL no es nula y cargar la imagen con Picasso
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(binding.ivDetailsBackground)
        }

        onClicks()
    }

    private fun setupCircleButtonListeners() {
        binding.cvDetailsCircleDogFood.setOnClickListener {
            currentDescription = getString(R.string.tv_details_food)
            binding.tvDetailsText.text = currentDescription
            selectButton(it.id)
        }

        binding.cvDetailsCircleDoc.setOnClickListener {
            currentDescription = getString(R.string.tv_details_doc)
            binding.tvDetailsText.text = currentDescription
            selectButton(it.id)
        }

        binding.cvDetailsCircleHome.setOnClickListener {
            currentDescription = getString(R.string.tv_details_home)
            binding.tvDetailsText.text = currentDescription
            selectButton(it.id)
        }

        binding.cvDetailsCircleDogPaw.setOnClickListener {
            currentDescription = getString(R.string.tv_details_dog_paw)
            binding.tvDetailsText.text = currentDescription
            selectButton(it.id)
        }
    }

    private fun selectButton(buttonId: Int) {
        // Establece el fondo de los botones según estén seleccionados o no
        binding.cvDetailsCircleDoc.isSelected = buttonId == R.id.cv_details_circle_doc
        binding.cvDetailsCircleHome.isSelected = buttonId == R.id.cv_details_circle_home
        binding.cvDetailsCircleDogPaw.isSelected = buttonId == R.id.cv_details_circle_dog_paw

        selectedButtonId = buttonId
    }

    private fun onClicks() {
        binding.tvDetailsAdopt.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("imageUrl", imageUrl)
            Utils.startActivityWithSlideToLeft(this, AdoptDogActivity::class.java, bundle)
            finish()
        }
        binding.btBackWhiteDetails.isEnabled = false

        binding.btBackBlackDetails.setOnClickListener {
            binding.btBackBlackDetails.isEnabled = false

            binding.btBackBlackDetails.animate().apply {
                translationX(300f)
                interpolator = AccelerateDecelerateInterpolator()
                duration = 1000
                withEndAction {
                    finish()
                    binding.btBackBlackDetails.isEnabled = true
                }
            }
            binding.btBackWhiteDetails.isEnabled = false
        }
    }
}
