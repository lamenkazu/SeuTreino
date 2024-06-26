package com.example.seutreino.view.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seutreino.R
import com.example.seutreino.databinding.FragmentWorkoutHistoryBinding

class WorkoutHistoryFragment : Fragment() {

    lateinit var binding: FragmentWorkoutHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutHistoryBinding.inflate(layoutInflater)

        return binding.root
    }

}