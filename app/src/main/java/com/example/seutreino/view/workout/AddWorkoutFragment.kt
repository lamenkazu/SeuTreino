package com.example.seutreino.view.workout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seutreino.databinding.FragmentAddWorkoutBinding
import com.example.seutreino.model.entities.Workout
import com.example.seutreino.model.entities.value_objects.ExerciseWithDuration
import com.example.seutreino.util.UiState
import com.example.seutreino.util.hide
import com.example.seutreino.util.show
import com.example.seutreino.util.toast
import com.example.seutreino.view_model.ExerciseViewModel
import com.example.seutreino.view_model.WorkoutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWorkoutFragment : Fragment() {

    private val TAG: String = "AddWorkoutFragment"

    private val viewModel: WorkoutViewModel by viewModels()
    private val exerciseViewModel: ExerciseViewModel by viewModels()

    private var _binding: FragmentAddWorkoutBinding? = null
    private val binding get() = _binding!!

    val adapter by lazy{
        AddWorkoutExerciseListingAdapter(
            onItemSelectionChanged = {exerciseId, isSelected ->},
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddWorkoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.availableExercisesRecyclerView.adapter = adapter
        binding.availableExercisesRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        exerciseViewModel.getExercises()

        exerciseViewModel.exercises.observe(viewLifecycleOwner){state ->
            when(state){
                is UiState.Loading -> {
                    binding.progressBar.show()

                }

                is UiState.Failure -> {
                    binding.progressBar.hide()
                    toast(state.error.toString())
                }

                is UiState.Success -> {
                    binding.progressBar.hide()

                    val exerciseNamesMap: MutableMap<String, String> = mutableMapOf()
                    val exercisesWithDurationList = mutableListOf<ExerciseWithDuration>()

                    for(exercise in state.data){
                        exerciseNamesMap[exercise.id] = exercise.name

                        exercisesWithDurationList.add(
                            ExerciseWithDuration(
                                exerciseId = exercise.id,
                                durationInSeconds = 0
                            )
                        )
                    }

                    adapter.updateList(exercisesWithDurationList, exerciseNamesMap)

                }
            }
        }


        binding.addWorkoutButton.setOnClickListener{
            if(validation()){
                    viewModel.addWorkout(
                    Workout(
                        id="",
                        name= binding.workoutName.text.toString(),
                        description = binding.workoutDescription.text.toString(),
                        exercises = adapter.listOfSelectedExercises
                    )
                )
            }
        }

        viewModel.addWorkout.observe(viewLifecycleOwner){state ->
            when(state){
                is UiState.Loading -> {
                    binding.progressBar.show()

                }

                is UiState.Failure -> {
                    binding.progressBar.hide()
                    toast(state.error.toString())
                }

                is UiState.Success -> {
                    binding.progressBar.hide()

                    toast(state.data.toString())
                }
            }
        }

    }

    private fun validation(): Boolean{
        var isValid = true

        if(binding.workoutName.text.toString().isEmpty()){
            isValid = false
            toast("Enter Workout Name")
        }

        return isValid

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}