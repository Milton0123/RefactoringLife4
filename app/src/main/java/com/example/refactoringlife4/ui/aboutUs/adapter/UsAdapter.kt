package com.example.refactoringlife4.ui.aboutUs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.refactoringlife4.databinding.ItemRvUsAboutUsBinding
import com.example.refactoringlife4.utils.OnAboutUsClickListener
import com.squareup.picasso.Picasso

class UsAdapter(private val listOfUs: List<String>, private val listener: OnAboutUsClickListener) :
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
        holder.bind(us, listener)
    }
}

class UsHolder(private val binding: ItemRvUsAboutUsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(us: String, listener: OnAboutUsClickListener) {
        Picasso.get().load(us).into(binding.ivPhotoAboutUs)

        binding.ivPhotoAboutUs.setOnClickListener {
            listener.onMemberClick(us)
        }
    }
}
