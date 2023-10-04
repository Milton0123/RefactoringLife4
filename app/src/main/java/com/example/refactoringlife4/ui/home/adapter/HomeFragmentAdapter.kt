package com.example.refactoringlife4.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.refactoringlife4.databinding.ItemRvHomeDogsBinding
import android.view.LayoutInflater
import com.squareup.picasso.Picasso

class HomeFragmentAdapter(private val listOfDogs: List<String>, private val onClick: (Int) -> Any) :
    RecyclerView.Adapter<DogsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsHolder {
        val binding =
            ItemRvHomeDogsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogsHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfDogs.size
    }

    override fun onBindViewHolder(holder: DogsHolder, position: Int) {
        val dog = listOfDogs[position]
        holder.bind(dog, onClick)
    }
}

class DogsHolder(private val binding: ItemRvHomeDogsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(dog: String, onClick: (Int) -> Any) {
        Picasso.get().load(dog).into(binding.ivPhotoDogs)

        binding.ivPhotoDogs.setOnClickListener {
            onClick(adapterPosition)
        }
    }
}
