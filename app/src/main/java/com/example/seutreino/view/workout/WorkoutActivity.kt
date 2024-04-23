package com.example.seutreino.view.workout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.seutreino.databinding.ActivityWorkoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutActivity : AppCompatActivity() {

    val TAG: String = "WorkoutActivity"

    private lateinit var binding: ActivityWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.workoutToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}