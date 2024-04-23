package com.example.seutreino.view.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seutreino.databinding.ItemExerciseLayoutBinding
import com.example.seutreino.model.entities.Exercise

class ExerciseListingAdapter(
    val onItemClicked: (Int, Exercise) -> Unit,
    val onEditClicked: (Int, Exercise) -> Unit,
    val onDeleteClicked: (Int, Exercise) -> Unit,
): RecyclerView.Adapter<ExerciseListingAdapter.MyViewHolder>() {

    private var list: MutableList<Exercise> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ItemExerciseLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
    }

    fun updateList(list: MutableList<Exercise>){
        this.list = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        list.removeAt(position)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ItemExerciseLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Exercise){
            val imageUrl = item.image.toUri()
            Glide.with(binding.root.context)
                .load(imageUrl)
                .into(binding.exerciseImage)

            binding.exerciseTitle.text = item.name
            binding.exerciseObservations.text = item.observations
            binding.editButton.setOnClickListener{onEditClicked.invoke(adapterPosition, item)}
            binding.deleteButton.setOnClickListener{onDeleteClicked.invoke(adapterPosition, item)}
            binding.exerciseItemLayout.setOnClickListener{onItemClicked.invoke(adapterPosition, item)}
        }
    }
}