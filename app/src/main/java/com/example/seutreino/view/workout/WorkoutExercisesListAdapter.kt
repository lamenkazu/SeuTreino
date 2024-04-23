package com.example.seutreino.view.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seutreino.databinding.ItemWorkoutExerciseLayoutBinding
import com.example.seutreino.model.entities.value_objects.ExerciseWithDuration

class WorkoutExercisesListAdapter(
    private val onItemSelectionChanged: (exerciseId: String, isSelected: Boolean) -> Unit,
    ): RecyclerView.Adapter<WorkoutExercisesListAdapter.MyViewHolder>() {

    private var exerciseNamesMap: Map<String, String> = emptyMap()
    var completedExerciseIds = mutableSetOf<String>()
    var list: MutableList<ExerciseWithDuration> = arrayListOf()



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

    fun setList(list: MutableList<ExerciseWithDuration>, exerciseNamesMap: Map<String, String>){
        this.list.clear()
        this.list.addAll(list)
        this.exerciseNamesMap = exerciseNamesMap
        notifyDataSetChanged()
    }

    fun markExerciseComplete(exerciseId: String) {
        completedExerciseIds.add(exerciseId)

        // Encontra os exercícios completados na lista.
        val indexOfCompletedExercise = list.indexOfFirst { it.exerciseId == exerciseId }
        if (indexOfCompletedExercise != -1) {
            notifyItemChanged(indexOfCompletedExercise)
        }
    }

    fun getNextExercise(): ExerciseWithDuration? {
        for (exercise in list) {
            if (!completedExerciseIds.contains(exercise.exerciseId)) {

                return exercise
            }
        }
        return null

    }

    fun getExerciseName(id: String): String? {
        return exerciseNamesMap[id]
    }


    inner class MyViewHolder(val binding: ItemWorkoutExerciseLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ExerciseWithDuration){
            val exerciseName = exerciseNamesMap[item.exerciseId]
            binding.workoutExerciseName.text = exerciseName

            // Converte duração em segundos para minutos e segundos
            val minutes = item.durationInSeconds / 60
            val seconds = item.durationInSeconds % 60

            // Formata valores como strings com zeros à esquerda
            val formattedMinutes = minutes.toString().padStart(2, '0')
            val formattedSeconds = seconds.toString().padStart(2, '0')

            // Atualiza campos de texto
            binding.minutesSelector.setText(formattedMinutes)
            binding.secondsSelector.setText(formattedSeconds)

            // Verifica se o exercício está completo
            val isCompleted = completedExerciseIds.contains(item.exerciseId)

            // Marca visualmente o checkbox conforme o status do exercício
            binding.selectWorkoutExercise.isChecked = isCompleted

            binding.selectWorkoutExercise.setOnCheckedChangeListener { _, isChecked ->
                // Atualiza o conjunto de IDs de exercícios concluídos
                if (isChecked) {
                    completedExerciseIds.add(item.exerciseId)
                } else {
                    completedExerciseIds.remove(item.exerciseId)
                }
                // Notifica sobre a mudança de seleção do item
                onItemSelectionChanged(item.exerciseId, isChecked)
            }
        }
    }
}