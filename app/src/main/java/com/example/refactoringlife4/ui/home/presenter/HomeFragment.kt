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
import com.example.refactoringlife4.databinding.FragmentHomeBinding
import com.example.refactoringlife4.ui.Details.DetailsActivity
import com.example.refactoringlife4.ui.all_dog.presenters.AllDogActivity
import com.example.refactoringlife4.ui.home.adapter.HomeFragmentAdapter

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var listDogs: List<String>
    private var images: List<String> = emptyList()

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
        // Configurar el RecyclerView y el adaptador
        val recyclerView = binding.homeRvDogs
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val listOfDogs = images

        val adapter = HomeFragmentAdapter(listOfDogs) { position ->
            // Función de clic en un elemento del RecyclerView
            onItemClick(position)
        }
        recyclerView.adapter = adapter

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
        binding.btHomeDog.setOnClickListener {
            startActivity(Intent(requireContext(), AllDogActivity::class.java))
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

    private fun showSuccess(images: List<String>) {
        listDogs = images // Asignar la lista de perros a listDogs
        initRecyclerView(listDogs)
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
        val dogsAdapter = HomeFragmentAdapter(listDogs) { position ->
            onItemClick(position)
        }

        binding.homeRvDogs.apply {
            adapter = dogsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun onItemClick(position: Int) {
        val selectedDogImage = listDogs[position] // Esto debe ser la URL de la imagen
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra("imageUrl", selectedDogImage)
        startActivity(intent)
    }
}
