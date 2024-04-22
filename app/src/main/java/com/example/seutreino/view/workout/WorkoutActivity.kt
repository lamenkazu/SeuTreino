package com.example.seutreino.view.workout

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.seutreino.R
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


        try {
            val navController: NavController = findNavController(R.id.nav_host_fragment_content_workout);

            val local = intent.extras?.getString("local")
            if(!local.equals("add")){
                val navInflater = navController.navInflater
                val graph = navInflater.inflate(R.navigation.nav_graph4)
                navController.graph = graph
            }

        } catch (e: Exception) {
            // Handle error: NavController not found
            e.printStackTrace();
            // OR finish(); // Considerando fechar a atividade como alternativa
        }

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}