package com.example.seutreino.view.workout

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seutreino.databinding.ItemWorkoutExerciseLayoutBinding
import com.example.seutreino.model.entities.value_objects.ExerciseWithDuration

class AddWorkoutExerciseListingAdapter(
    private val onItemSelectionChanged: (exerciseId: String, isSelected: Boolean) -> Unit,
) : RecyclerView.Adapter<AddWorkoutExerciseListingAdapter.MyViewHolder>() {

    private var list: MutableList<ExerciseWithDuration> = arrayListOf()
    private var exerciseNamesMap: Map<String, String> = emptyMap()

    var listOfSelectedExercises: MutableList<ExerciseWithDuration> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ItemWorkoutExerciseLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
    }

    fun updateList(list: MutableList<ExerciseWithDuration>, exerciseNamesMap: Map<String, String>){
        this.list.clear()
        this.list.addAll(list)
        this.exerciseNamesMap = exerciseNamesMap
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ItemWorkoutExerciseLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: ExerciseWithDuration){
            val exerciseName = exerciseNamesMap[item.exerciseId]
            binding.workoutExerciseName.text = exerciseName

            val minutes = item.durationInSeconds / 60
            val seconds = item.durationInSeconds % 60

            binding.minutesSelector.setText(minutes.toString().padStart(2, '0'))
            binding.secondsSelector.setText(seconds.toString().padStart(2, '0'))

            if(!binding.selectWorkoutExercise.isChecked){
                binding.minutesSelector.setTextColor(Color.GRAY)
                binding.secondsSelector.setTextColor(Color.GRAY)
            }

            binding.minutesSelector.isEnabled = binding.selectWorkoutExercise.isChecked
            binding.secondsSelector.isEnabled = binding.selectWorkoutExercise.isChecked

            binding.minutesSelector.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val newMinutes = s.toString().toIntOrNull() ?: 0
                    if (binding.selectWorkoutExercise.isChecked) {
                        val newDuration = newMinutes * 60 + binding.secondsSelector.text.toString().toInt()

                        item.durationInSeconds = newDuration
                    }
                }
            })

            binding.secondsSelector.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val newSeconds = s.toString().toIntOrNull() ?: 0
                    if (binding.selectWorkoutExercise.isChecked) {
                        val newDuration = binding.minutesSelector.text.toString().toInt()  * 60 + newSeconds
                        item.durationInSeconds = newDuration

                    }
                }
            })


            binding.selectWorkoutExercise.setOnCheckedChangeListener { _, isChecked ->
                onItemSelectionChanged(item.exerciseId, isChecked)


                if (isChecked) {
                    binding.minutesSelector.setTextColor(Color.BLACK)
                    binding.secondsSelector.setTextColor(Color.BLACK)

                    binding.minutesSelector.isEnabled = binding.selectWorkoutExercise.isChecked
                    binding.secondsSelector.isEnabled = binding.selectWorkoutExercise.isChecked


                    listOfSelectedExercises.add(item)
                } else {
                    binding.minutesSelector.setTextColor(Color.GRAY)
                    binding.secondsSelector.setTextColor(Color.GRAY)

                    binding.minutesSelector.isEnabled = binding.selectWorkoutExercise.isChecked
                    binding.secondsSelector.isEnabled = binding.selectWorkoutExercise.isChecked


                    listOfSelectedExercises.remove(item)
                }

            }

        }

    }

}