package com.example.seutreino.view.workout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seutreino.databinding.FragmentWorkoutListingBinding
import com.example.seutreino.util.UiState
import com.example.seutreino.util.hide
import com.example.seutreino.util.show
import com.example.seutreino.util.toast
import com.example.seutreino.view.exercise.ExerciseDetailActivity
import com.example.seutreino.view_model.WorkoutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutListingFragment : Fragment() {

    val TAG: String = "WorkoutListingFragment"
    lateinit var binding: FragmentWorkoutListingBinding

    private var deleteWorkoutPosition: Int = -1

    val viewModel: WorkoutViewModel by viewModels()
    val adapter by lazy{
        WorkoutListingAdapter(
            onItemClicked = {_, item ->
//                val intent = Intent(requireActivity(), ExerciseDetailActivity::class.java).apply{
//                    putExtra("ExerciseId", item.id)
//                    putExtra("edit", false)
//                }
//                startActivity(intent)

            },
            onDeleteClicked = {pos, item ->
                viewModel.deleteWorkout(item)
                deleteWorkoutPosition = pos
            },
            onStartClicked = {pos, item ->
                val intent = Intent(requireActivity(), StartWorkoutActivity::class.java).apply{
                    putExtra("WorkoutId", item.id)
                }
                startActivity(intent)
            }
        )
    }

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

        binding.workoutList.adapter = adapter
        binding.workoutList.layoutManager = LinearLayoutManager(requireContext())

        observeDelete()



    }

    override fun onResume() {
        super.onResume()
        observeList()
    }

    private fun observeDelete(){
        viewModel.deleteWorkout.observe(viewLifecycleOwner){state ->
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

                    toast(state.data)

                    if(deleteWorkoutPosition != -1){
                        adapter.removeItem(deleteWorkoutPosition)

                    }

                }
            }
        }
    }

    private fun observeList(){
        viewModel.getWorkouts()

        viewModel.workouts.observe(viewLifecycleOwner){state ->
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
                    adapter.updateList(state.data.toMutableList())

                }
            }

        }
    }
}