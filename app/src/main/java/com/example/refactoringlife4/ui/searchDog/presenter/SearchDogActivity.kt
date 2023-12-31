package com.example.refactoringlife4.ui.searchDog.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.refactoringlife4.ui.searchDog.viewModel.SearchOneDogViewModel
import com.example.refactoringlife4.ui.searchDog.viewModel.SearchViewModelEvent
import com.example.refactoringlife4.databinding.ActivitySearchDogBinding
import com.example.refactoringlife4.ui.details.presenter.DetailsActivity
import com.example.refactoringlife4.ui.searchDog.viewModel.SearchViewModelFactory
import com.squareup.picasso.Picasso

class SearchDogActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchDogBinding
    private lateinit var viewModel: SearchOneDogViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getViewModel()
        calls("labrador")
        onClicks()
        observe()
        searchDog()

    }

    private fun onClicks() {
        binding.svSearchSearchDog.setOnClickListener {
            binding.svSearchSearchDog.isIconified = false
        }
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

    private fun calls(imageDog: String) {
        viewModel.getOneDog(imageDog)
    }

    fun observe() {
        viewModel.data.observe(this) {
            when (it) {
                is SearchViewModelEvent.ShowSuccessView -> {
                    if (it.image.isNullOrEmpty()) {
                        viewModel.getOneDog(
                            binding.svSearchSearchDog.query.toString()
                        )
                    } else {
                        Picasso.get().load(it.image).into(binding.icImageOneDogSearch.ivImageOneDog)
                        onItemClick(it.image)
                    }
                }
                is SearchViewModelEvent.ShowError -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "no carga", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getViewModel() {
        viewModel =
            SearchViewModelFactory(applicationContext).create(SearchOneDogViewModel::class.java)
    }

    private fun searchDog() {
        binding.svSearchSearchDog.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getOneDog(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun onItemClick(value: String) {
        binding.icImageOneDogSearch.ivImageOneDog.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("imageUrl", value)
            startActivity(intent)
        }
    }

}
