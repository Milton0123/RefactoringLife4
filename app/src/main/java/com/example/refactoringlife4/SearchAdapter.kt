package com.example.refactoringlife4
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.refactoringlife4.databinding.ItemOneDogBinding
import com.example.refactoringlife4.ui.home.adapter.DogsHolder
import com.squareup.picasso.Picasso


class SearchAdapter(private val dogImage: List<String>, private val onClick: (Int)-> Any): RecyclerView.Adapter<OneDogHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneDogHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_one_dog, parent, false)
        return OneDogHolder(view)
    }

    override fun onBindViewHolder(holder: OneDogHolder, position: Int) {
        holder.render(dogImage[position],onClick)
    }

    override fun getItemCount(): Int {
        return dogImage.size
    }
}

class OneDogHolder(view:View):RecyclerView.ViewHolder(view){

private val binding= ItemOneDogBinding.bind(view)

fun render(dog:String, onClick: (Int) -> Any){
    Picasso.get().load(dog).into(binding.ivImageOneDog)

    binding.ivImageOneDog.setOnClickListener {
        onClick(adapterPosition)
    }
}
}