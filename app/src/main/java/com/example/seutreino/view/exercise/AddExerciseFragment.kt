package com.example.seutreino.view.exercise

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
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
    private val viewModel: ExerciseViewModel by viewModels()

    private var _binding: FragmentAddExerciseBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageUri: Uri

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { selectedImageUri ->
                binding.exerciseImage.setImageURI(selectedImageUri)
                Log.d(TAG, selectedImageUri.toString())
                imageUri = selectedImageUri
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddExerciseBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseImage.setOnClickListener{
            pickImage.launch("image/*")
        }

        binding.submitExerciseButton.setOnClickListener{
            if(validation()){
                viewModel.onUploadSingleFile(imageUri) { state ->

                    when (state) {
                        is UiState.Loading -> {
                            binding.progressBar.show()

                        }

                        is UiState.Failure -> {
                            binding.progressBar.hide()
                            toast(state.error.toString())
                        }

                        is UiState.Success -> {
                            binding.progressBar.hide()

                            viewModel.addExercise(
                                Exercise(
                                    id = "",
                                    name = binding.exerciseNameInput.text.toString(),
                                    image = state.data.toString(),
                                    observations = binding.exerciseObservationsInput.text.toString()
                                ),
                                state.data
                            )
                        }
                    }
                }
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
        var isValid = true

        if(binding.exerciseNameInput.text.toString().isEmpty()){
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