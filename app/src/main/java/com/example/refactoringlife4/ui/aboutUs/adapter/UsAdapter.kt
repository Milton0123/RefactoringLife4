package com.example.refactoringlife4.ui.aboutUs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.refactoringlife4.databinding.ItemRvUsAboutUsBinding
import com.squareup.picasso.Picasso

class UsAdapter(private val listOfUs: List<String>, private val onClick: (Int)-> Any) :
    RecyclerView.Adapter<UsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsHolder {
        val binding =
            ItemRvUsAboutUsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfUs.size
    }

    override fun onBindViewHolder(holder: UsHolder, position: Int) {
        val us = listOfUs[position]
        holder.bind(us, onClick)
    }
}

class UsHolder(private val binding: ItemRvUsAboutUsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(us: String, onClick: (Int)-> Any) {
        Picasso.get().load(us).into(binding.ivPhotoAboutUs)

        binding.ivPhotoAboutUs.setOnClickListener {
            onClick(adapterPosition)
        }
    }
}