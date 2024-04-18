package com.example.seutreino.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seutreino.R
import com.example.seutreino.databinding.FragmentExerciseListingBinding
import com.example.seutreino.databinding.FragmentWorkoutListingBinding

class WorkoutListingFragment : Fragment() {

    val TAG: String = "WorkoutListingFragment"
    lateinit var binding: FragmentWorkoutListingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentWorkoutListingBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}