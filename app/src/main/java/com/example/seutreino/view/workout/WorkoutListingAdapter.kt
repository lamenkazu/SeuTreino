package com.example.seutreino.view.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seutreino.databinding.ItemWorkoutLayoutBinding
import com.example.seutreino.model.entities.Workout

class WorkoutListingAdapter(
    val onItemClicked: (Int, Workout) -> Unit,
    val onDeleteClicked: (Int, Workout) -> Unit,
    val onStartClicked: (Int, Workout) -> Unit,
):  RecyclerView.Adapter<WorkoutListingAdapter.MyViewHolder>(){

    private var list: MutableList<Workout> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkoutListingAdapter.MyViewHolder {
        val itemView = ItemWorkoutLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorkoutListingAdapter.MyViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<Workout>){
        this.list = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        list.removeAt(position)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ItemWorkoutLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Workout){
            binding.workoutName.text = item.name
            binding.workoutDescription.text = item.description

            binding.workoutItemLayout.setOnClickListener{
                onItemClicked.invoke(adapterPosition, item)
            }

            binding.deleteButton.setOnClickListener{
                onDeleteClicked.invoke(adapterPosition, item)
            }

            binding.startWorkoutButton.setOnClickListener {
                onStartClicked.invoke(adapterPosition, item)
            }

        }
    }

}