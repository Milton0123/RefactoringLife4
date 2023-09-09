package com.example.refactoringlife4.ui.home.presenter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.refactoringlife4.ui.home.viewmodel.HomeViewModel
import com.example.refactoringlife4.ui.home.viewmodel.HomeViewModelEvent
import com.example.refactoringlife4.ui.home.viewmodel.HomeViewModelFactory
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.refactoringlife4.ui.searchDog.presenter.SearchDogActivity
import com.example.refactoringlife4.databinding.FragmentHomeBinding
import com.example.refactoringlife4.ui.aboutUs.presenter.AboutUsActivity
import com.example.refactoringlife4.ui.all_dog.presenters.AllDogActivity
import com.example.refactoringlife4.ui.home.adapter.HomeFragmentAdapter
import com.example.refactoringlife4.utils.Utils

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        getViewModel()
        observer()
        onClick()
        calls()


        return binding.root
    }

    private fun observer() {

        viewModel.data.observe(viewLifecycleOwner) {
            when (it) {

                is HomeViewModelEvent.ShowSuccessView -> {
                    showSuccess(it.images)
                }

                is HomeViewModelEvent.ShowError -> {
                    showError()
                }

                else -> {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onClick() {
        binding.btHomeUs.setOnClickListener {
            goToAboutUs()
        }
        binding.btHomeDog.setOnClickListener {
            goToAllDogs()
        }
        binding.btHomeSearch.setOnClickListener {
            startActivity(Intent(requireContext(), SearchDogActivity::class.java))

        }
    }

    private fun calls() {
        showLoading()
        viewModel.getDogs()
    }

    private fun actions() {
        binding.errorView.root.setOnClickListener {
            calls()
        }
    }

    private fun goToAllDogs() {
        Utils.startActivityWithSlideToLeft(requireContext(), AllDogActivity::class.java, null)
    }

    private fun goToAboutUs() {
        Utils.startActivityWithSlideToLeft(requireContext(), AboutUsActivity::class.java, null)
    }

    private fun showSuccess(images: List<String>) {
        initRecyclerView(images)
        binding.errorView.root.visibility = View.GONE
        binding.loadingView.root.visibility = View.GONE
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
        viewModel = HomeViewModelFactory(requireContext()).create(HomeViewModel::class.java)
    }

    private fun initRecyclerView(listDogs: List<String>) {
        val dogsAdapter = HomeFragmentAdapter(listDogs) {
            //add function onClick
        }

        binding.homeRvDogs.apply {
            adapter = dogsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}
