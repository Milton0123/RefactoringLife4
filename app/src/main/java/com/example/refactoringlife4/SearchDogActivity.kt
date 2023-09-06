package com.example.refactoringlife4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.refactoringlife4.databinding.ActivitySearchDogBinding

class SearchDogActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchDogBinding
    private val viewModel by viewModels<SearchOneDogViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchDogBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    private fun actions() {
        calls()
    }

    fun observe() {
        viewModel.data.observe(this) {
            when (it) {
                is SearchViewModelEvent.ShowSuccessView -> {
                    showSuccess(it.image)
                }
                is SearchViewModelEvent.ShowError -> {
                    showError()
                }
                else-> {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun calls() {
        viewModel.getOneDog(breed = String())
    }


    private fun showSuccess(image: List<String>) {
        initRecyclerView(image)
    }

    private fun showLoading() {
    }

    private fun showError() {
        actions()
    }


    private fun initRecyclerView(imageDog: List<String>) {
        val OnedogAdapter = SearchAdapter(imageDog) {
            //add function onClick
        }
    }

    private fun SearchDog(){
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
}