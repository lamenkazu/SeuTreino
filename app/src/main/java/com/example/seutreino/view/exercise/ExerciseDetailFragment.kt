package com.example.seutreino.view.exercise

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.seutreino.databinding.FragmentExerciseDetailBinding
import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.util.UiState
import com.example.seutreino.util.hide
import com.example.seutreino.util.show
import com.example.seutreino.util.toast
import com.example.seutreino.view_model.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseDetailFragment : Fragment() {

    val TAG: String = "ExerciseDetailFragment"
    val viewModel: ExerciseViewModel by viewModels()

    private var _binding: FragmentExerciseDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var id: String
    private var isEdit: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentExerciseDetailBinding.inflate(inflater, container, false)

        id = requireActivity().intent.extras?.getString("ExerciseId")!!
        isEdit = requireActivity().intent.extras?.getBoolean("edit")!!

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!isEdit){
            binding.updateExerciseButton.visibility = View.GONE

            binding.exerciseName.apply {
                isFocusable = false
                isClickable = false
            }

            binding.exerciseObservations.apply {
                isFocusable = false
                isClickable = false
            }

        }else{
            binding.updateExerciseButton.setOnClickListener{
                if(validation()){
                    viewModel.updateExercise(
                        Exercise(
                            id=id,
                            name = binding.exerciseName.text.toString(),
                            image = "",
                            observations = binding.exerciseObservations.text.toString()
                        )
                    )
                }
            }

            viewModel.updateExercise.observe(viewLifecycleOwner){state ->
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
                    }
                }

            }
        }

        viewModel.getExerciseById(id)

        viewModel.exercise.observe(viewLifecycleOwner){state ->
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

                    binding.exerciseName.setText(state.data.name)
                    binding.exerciseObservations.setText(state.data.observations)

                }
            }
        }


    }

    private fun validation(): Boolean{
        var isValid = true;

        if(binding.exerciseName.text.toString().isEmpty()){
            isValid = false
            toast("Cant Save Without a Name")
        }

        return isValid;
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}