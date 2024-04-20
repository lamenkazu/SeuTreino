package com.example.seutreino.view.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.seutreino.databinding.FragmentAddExerciseBinding
import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.util.UiState
import com.example.seutreino.util.hide
import com.example.seutreino.util.show
import com.example.seutreino.util.toast
import com.example.seutreino.view_model.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddExerciseFragment : Fragment() {

    val TAG: String = "AddExerciseFragment"
    val viewModel: ExerciseViewModel by viewModels()

    private var _binding: FragmentAddExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddExerciseBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitExerciseButton.setOnClickListener{
            if(validation()){
                viewModel.addExercise(
                    Exercise(
                        id="",
                        name = binding.exerciseNameInput.text.toString(),
                        image= "", //TODO
                        observations = binding.exerciseObservationsInput.text.toString()
                    )
                )
            }
        }

        viewModel.addExercise.observe(viewLifecycleOwner){state ->
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

    private fun validation(): Boolean{
        var isValid = true;

        if(binding.exerciseNameInput.text.toString().isNullOrEmpty()){
            isValid = false
            toast("Enter Name")
        }

        return isValid

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}