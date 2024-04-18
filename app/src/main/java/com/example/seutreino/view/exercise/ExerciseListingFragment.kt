package com.example.seutreino.view.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.seutreino.R
import com.example.seutreino.databinding.FragmentExerciseDetailBinding
import com.example.seutreino.databinding.FragmentExerciseListingBinding
import com.example.seutreino.view_model.ExerciseViewModel

class ExerciseListingFragment : Fragment() {

    val TAG: String = "ExerciseListingFragment"
    lateinit var binding: FragmentExerciseListingBinding

    val viewModel: ExerciseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseListingBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getExercises()

        viewModel.exercises.observe(viewLifecycleOwner, {

        })

    }
}