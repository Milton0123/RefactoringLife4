package com.example.refactoringlife4.ui.home.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.refactoringlife4.ui.home.presenter.viewmodel.HomeViewModel
import com.example.refactoringlife4.ui.home.presenter.viewmodel.HomeViewModelEvent
import com.example.refactoringlife4.ui.home.presenter.viewmodel.HomeViewModelFactory
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.refactoringlife4.databinding.FragmentHomeBinding
import com.example.refactoringlife4.ui.home.adapter.HomeFragmentAdapter

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
        calls()
        return binding.root
    }

    private fun observer() {

        viewModel.data.observe(viewLifecycleOwner) {
            when (it) {

                is HomeViewModelEvent.ShowSuccessView -> {
                    initRecyclerView(it.images)
                }
                is HomeViewModelEvent.ShowError -> {
                    showError(it.toString())
                }
                else -> {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun calls() {
        viewModel.getDogs()
    }

    private fun actions() {
        //navegacion a siguiente fragment
    }

    private fun showError(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun getViewModel() {
        viewModel = HomeViewModelFactory().create(HomeViewModel::class.java)
    }

    private fun initRecyclerView(listDogs:List<String>) {
        val dogsAdapter = HomeFragmentAdapter(listDogs)

        binding.homeRvDogs.apply {
            adapter = dogsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}
