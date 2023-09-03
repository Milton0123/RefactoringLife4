package com.example.refactoringlife4.ui.aboutUs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.refactoringlife4.databinding.ItemRvUsMembersBinding
import com.squareup.picasso.Picasso

class MembersAdapter(private val listOfMembers: List<String>, private val onClick: (Int)-> Any) :
    RecyclerView.Adapter<MembersHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersHolder {
        val binding =
            ItemRvUsMembersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MembersHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfMembers.size
    }

    override fun onBindViewHolder(holder: MembersHolder, position: Int) {
        val us = listOfMembers[position]
        holder.bind(us, onClick)
    }
}

class MembersHolder(private val binding: ItemRvUsMembersBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(members: String, onClick: (Int)-> Any) {
        Picasso.get().load(members).into(binding.ivPhotoMembers)

        binding.ivPhotoMembers.setOnClickListener {
            onClick(adapterPosition)
        }
    }
}