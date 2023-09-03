package com.example.refactoringlife4.ui.aboutUs.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.refactoringlife4.R
import com.example.refactoringlife4.databinding.ActivityAboutUsBinding
import com.example.refactoringlife4.ui.aboutUs.adapter.UsAdapter
import com.example.refactoringlife4.ui.aboutUs.viewmodel.AboutUsViewModel
import com.example.refactoringlife4.ui.aboutUs.viewmodel.AboutUsViewModelEvent
import com.example.refactoringlife4.ui.aboutUs.viewmodel.AboutUsViewModelFactory
import com.example.refactoringlife4.ui.home.adapter.HomeFragmentAdapter

class AboutUsActivity : AppCompatActivity() {

    private lateinit var viewModel: AboutUsViewModel
    private lateinit var binding: ActivityAboutUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getViewModel()
        observer()
        action()
    }

    private fun observer(){
        viewModel.data.observe(this){
            when(it){
                is AboutUsViewModelEvent.ShowSuccessView->{
                    showSuccessUs(it.images)
                }
            }
        }
    }

    private fun action(){
        viewModel.getUs()
    }

    private fun getViewModel() {
        viewModel = AboutUsViewModelFactory().create(AboutUsViewModel::class.java)
    }

    private fun showSuccessUs(images: List<String>) {
        initRecyclerView(images)
    }

    private fun initRecyclerView(listUs: List<String>) {
        val usAdapter = UsAdapter(listUs) {
            //add function onClick
        }

        binding.usRvAboutUs.apply {
            adapter = usAdapter
            layoutManager =
                LinearLayoutManager(this@AboutUsActivity,LinearLayoutManager.HORIZONTAL,false)
        }
    }
}