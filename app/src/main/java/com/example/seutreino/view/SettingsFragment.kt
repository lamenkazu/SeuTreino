package com.example.seutreino.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seutreino.R
import com.example.seutreino.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentSettingsBinding.inflate(layoutInflater)

        return binding.root
    }

}