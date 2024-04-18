package com.example.seutreino.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seutreino.R
import com.example.seutreino.databinding.FragmentExerciseDetailBinding
import com.example.seutreino.databinding.FragmentExerciseListingBinding

class ExerciseListingFragment : Fragment() {

    val TAG: String = "ExerciseListingFragment"
    lateinit var binding: FragmentExerciseListingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExerciseListingBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}