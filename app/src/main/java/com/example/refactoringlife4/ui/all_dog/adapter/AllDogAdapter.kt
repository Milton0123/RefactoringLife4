package com.example.refactoringlife4.ui.all_dog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.refactoringlife4.R
import com.example.refactoringlife4.databinding.ItemRvAllDogsBinding
import com.squareup.picasso.Picasso

class AllDogAdapter(private val randomListDogs: List<String>) :
    RecyclerView.Adapter<AllDogHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllDogHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_all_dogs, parent, false)
        return AllDogHolder(view)
    }

    override fun onBindViewHolder(holder: AllDogHolder, position: Int) {
        holder.render(randomListDogs[position])
    }

    override fun getItemCount(): Int {
        return randomListDogs.size
    }
}

class AllDogHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemRvAllDogsBinding.bind(view)
    fun render(value: String) {
        Picasso.get().load(value).into(binding.imageRandomDogs)
    }

}
