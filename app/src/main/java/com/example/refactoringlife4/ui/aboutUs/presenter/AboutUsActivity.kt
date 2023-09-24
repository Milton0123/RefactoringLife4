package com.example.refactoringlife4.ui.aboutUs.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.refactoringlife4.R
import com.example.refactoringlife4.databinding.ActivityAboutUsBinding
import com.example.refactoringlife4.ui.aboutUs.adapter.MembersAdapter
import com.example.refactoringlife4.ui.aboutUs.adapter.UsAdapter
import com.example.refactoringlife4.ui.aboutUs.viewmodel.AboutUsViewModel
import com.example.refactoringlife4.ui.aboutUs.viewmodel.AboutUsViewModelEvent
import com.example.refactoringlife4.ui.aboutUs.viewmodel.AboutUsViewModelFactory
import com.example.refactoringlife4.utils.OnAboutUsClickListener
import com.example.refactoringlife4.ui.home.presenter.HomeActivity
import com.example.refactoringlife4.utils.Utils
import com.squareup.picasso.Picasso

class AboutUsActivity : AppCompatActivity(), OnAboutUsClickListener {

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
        onClick()
    }

    private fun observer() {
        viewModel.dataUs.observe(this) {
            when (it) {
                is AboutUsViewModelEvent.ShowSuccessView -> {
                    showSuccessUs(it.images)
                    showSuccessMembers(it.images)
                }
            }
        }
        viewModel.dataMembers.observe(this) {
            when (it) {
                is AboutUsViewModelEvent.ShowSuccessView -> {
                    showSuccessMembers(it.images)
                }
            }
        }
    }

    private fun onClick() {
        binding.ivBackHome.setOnClickListener {
            goToBack()
        }
        binding.ivZoom.setOnClickListener {
            it.visibility = View.GONE
        }
    }

    private fun goToBack() {
        Utils.startActivityWithSlideToRight(this, HomeActivity::class.java, null)
    }

    private fun action() {
        viewModel.getUs()
        viewModel.getMembers()
    }

    private fun getViewModel() {
        viewModel = AboutUsViewModelFactory().create(AboutUsViewModel::class.java)
    }

    private fun showSuccessUs(images: List<String>) {
        initRecyclerViewUs(images)
    }

    private fun showSuccessMembers(images: List<String>) {
        initRecyclerViewMembers(images)
    }

    private fun initRecyclerViewMembers(listMembers: List<String>) {

        val membersAdapter = MembersAdapter(listMembers, this)
        binding.usRvMembers.apply {
            adapter = membersAdapter
            layoutManager =
                LinearLayoutManager(this@AboutUsActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun initRecyclerViewUs(listUs: List<String>) {
        val usAdapter = UsAdapter(listUs, this)

        binding.usRvAboutUs.apply {
            adapter = usAdapter
            layoutManager =
                LinearLayoutManager(this@AboutUsActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onBackPressed() {
        goToBack()
    }

    override fun onMemberClick(imageUrl: String) {
        Picasso.get().load(imageUrl).into(binding.ivZoom)
        binding.ivZoom.visibility = View.VISIBLE
    }
}
