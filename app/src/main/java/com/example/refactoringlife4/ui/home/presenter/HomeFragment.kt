package com.example.refactoringlife4.ui.home.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.refactoringlife4.R
import com.example.refactoringlife4.databinding.FragmentHomeBinding
import com.example.refactoringlife4.model.dto.Dog
import com.example.refactoringlife4.ui.home.adapter.HomeFragmentAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        val listOfDogs = createListOfDogs()
        val dogsAdapter = HomeFragmentAdapter(listOfDogs)

        binding.homeRvDogs.apply {
            adapter = dogsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun createListOfDogs(): List<Dog> {
        return listOf(
            // creada para harcodear perros en recyclerview
            Dog("Dog 1", R.drawable.img_dog_adoptme),
            Dog("Dog 2", R.drawable.img_dog_adoptme),
            Dog("Dog 3", R.drawable.img_dog_adoptme),
            Dog("Dog 4", R.drawable.img_dog_adoptme),
            Dog("Dog 5", R.drawable.img_dog_adoptme),
            Dog("Dog 6", R.drawable.img_dog_adoptme),
            Dog("Dog 7", R.drawable.img_dog_adoptme),
            Dog("Dog 8", R.drawable.img_dog_adoptme)
        )
    }
}
