package com.example.seutreino.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.seutreino.R
import com.example.seutreino.databinding.FragmentSettingsBinding
import com.example.seutreino.util.UiState
import com.example.seutreino.util.hide
import com.example.seutreino.util.show
import com.example.seutreino.util.toast
import com.example.seutreino.view.auth.AuthActivity
import com.example.seutreino.view.exercise.ExerciseDetailActivity
import com.example.seutreino.view_model.AuthViewModel

class SettingsFragment : Fragment() {

    lateinit var binding: FragmentSettingsBinding

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentSettingsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signOutButton.setOnClickListener {
            viewModel.logout {
                val intent = Intent(requireActivity(), AuthActivity::class.java)
                startActivity(intent)

                onDestroy()
            }
        }

    }



}