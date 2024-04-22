package com.example.seutreino.view.workout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.seutreino.R
import com.example.seutreino.databinding.FragmentExerciseListingBinding
import com.example.seutreino.databinding.FragmentWorkoutListingBinding
import com.example.seutreino.util.UiState
import com.example.seutreino.view_model.ExerciseViewModel
import com.example.seutreino.view_model.WorkoutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutListingFragment : Fragment() {

    val TAG: String = "WorkoutListingFragment"
    lateinit var binding: FragmentWorkoutListingBinding

    val viewModel: WorkoutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutListingBinding.inflate(layoutInflater)

        binding.addWorkoutButton.setOnClickListener {
            val intent = Intent(requireActivity(), WorkoutActivity::class.java).apply {
                putExtra("local", "add")
            }
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getWorkouts()

        viewModel.workouts.observe(viewLifecycleOwner){state ->
            when(state){
                is UiState.Loading -> {
                    Log.d(TAG, "Loading")

                }

                is UiState.Failure -> {
                    Log.e(TAG, state.error.toString())
                }

                is UiState.Success -> {
                    state.data.forEach{
                        Log.d(TAG, it.toString())
                    }
                }
            }

        }

    }
}