package com.example.seutreino.view.exercise

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seutreino.databinding.FragmentExerciseListingBinding
import com.example.seutreino.util.UiState
import com.example.seutreino.util.hide
import com.example.seutreino.util.show
import com.example.seutreino.util.toast
import com.example.seutreino.view_model.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseListingFragment : Fragment() {

    val TAG: String = "ExerciseListingFragment"
    lateinit var binding: FragmentExerciseListingBinding

    val viewModel: ExerciseViewModel by viewModels()
    val adapter by lazy{
        ExerciseListingAdapter(
            onItemClicked = {pos, item ->
                val intent = Intent(requireActivity(), ExerciseActivity::class.java).apply{
                    putExtra("ExerciseId", item.id)
                    putExtra("edit", false)
                }
                startActivity(intent)

            },
            onEditClicked = {pos, item ->
                val intent = Intent(requireActivity(), ExerciseActivity::class.java).apply{
                    putExtra("ExerciseId", item.id)
                    putExtra("edit", true)
                }
                startActivity(intent)
            },
            onDeleteClicked = {pos, item ->

            }
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentExerciseListingBinding.inflate(layoutInflater)

        binding.addExerciseButton.setOnClickListener {
            val intent = Intent(requireActivity(), ExerciseActivity::class.java).apply{
                putExtra("local", "add")
            }
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState:  Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.exerciseList.adapter = adapter
        binding.exerciseList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getExercises()

        viewModel.exercises.observe(viewLifecycleOwner){state ->
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

                    Log.d(TAG, state.data.toString())

                    adapter.updateList(state.data.toMutableList())
                }
            }
        }

    }
}