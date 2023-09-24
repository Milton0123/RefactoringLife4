package com.example.refactoringlife4.ui.aboutUs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.refactoringlife4.databinding.ItemRvUsMembersBinding
import com.example.refactoringlife4.utils.OnAboutUsClickListener
import com.squareup.picasso.Picasso

class MembersAdapter(
    private val listOfMembers: List<String>,
    private val listener: OnAboutUsClickListener
) :
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
        holder.bind(us, listener)
    }
}

class MembersHolder(private val binding: ItemRvUsMembersBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(members: String, listener: OnAboutUsClickListener) {
        Picasso.get().load(members).into(binding.ivPhotoMembers)

        binding.ivPhotoMembers.setOnClickListener {
            listener.onMemberClick(members)
        }
    }
}
