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

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_exercise)

//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Decide qual o grafico usar.
        val local = intent.extras?.getString("local")
        if(local.equals("add")){
            val navInflater = navController.navInflater
            val graph = navInflater.inflate(R.navigation.nav_graph)
            navController.graph = graph
        }

    }

    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_exercise)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()

        onBackPressed()
        return true
    }
}