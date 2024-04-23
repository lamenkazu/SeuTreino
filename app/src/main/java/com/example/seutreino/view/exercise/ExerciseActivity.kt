package com.example.seutreino.view.exercise

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.seutreino.R
import com.example.seutreino.databinding.ActivityExerciseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.exerciseToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title = ""

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}