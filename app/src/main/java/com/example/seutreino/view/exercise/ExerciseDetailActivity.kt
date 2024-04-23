package com.example.seutreino.view.exercise

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.seutreino.R
import com.example.seutreino.databinding.ActivityExerciseDetailBinding
import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.util.UiState
import com.example.seutreino.util.hide
import com.example.seutreino.util.show
import com.example.seutreino.view_model.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseDetailActivity : AppCompatActivity() {

    val TAG: String = "ExerciseDetailActivity"
    private val viewModel: ExerciseViewModel by viewModels()

    private lateinit var binding: ActivityExerciseDetailBinding

    private lateinit var id: String
    private var isEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExerciseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        id = intent.extras?.getString("ExerciseId")!!
        isEdit = intent.extras?.getBoolean("edit")!!

        Log.d(TAG, id)
        Log.d(TAG, isEdit.toString())

        setSupportActionBar(binding.exerciseToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title = ""



        if(!isEdit){
            binding.updateExerciseButton.visibility = View.GONE

            binding.exerciseName.apply {
                isFocusable = false
                isClickable = false
            }

            binding.exerciseObservations.apply {
                isFocusable = false
                isClickable = false
            }

        }else{
            binding.updateExerciseButton.setOnClickListener{
                if(validation()){
                    viewModel.updateExercise(
                        Exercise(
                            id=id,
                            name = binding.exerciseName.text.toString(),
                            image = "",
                            observations = binding.exerciseObservations.text.toString()
                        )
                    )
                }
            }

            viewModel.updateExercise.observe(this){state ->
                when(state){
                    is UiState.Loading -> {
                        binding.progressBar.show()

                    }

                    is UiState.Failure -> {
                        binding.progressBar.hide()
                        Toast.makeText(this, state.error.toString(), Toast.LENGTH_SHORT).show()
                    }

                    is UiState.Success -> {
                        binding.progressBar.hide()

                        Toast.makeText(this, state.data, Toast.LENGTH_SHORT).show()

                    }
                }

            }
        }

        viewModel.getExerciseById(id)

        viewModel.exercise.observe(this){state ->
            when(state){
                is UiState.Loading -> {
                    binding.progressBar.show()

                }

                is UiState.Failure -> {
                    binding.progressBar.hide()
                    Toast.makeText(this, state.error.toString(), Toast.LENGTH_SHORT).show()

                }

                is UiState.Success -> {
                    binding.progressBar.hide()

                    Log.d(TAG, state.data.toString())

                    binding.exerciseName.setText(state.data.name)
                    binding.exerciseObservations.setText(state.data.observations)

                }
            }
        }



    }


    private fun validation(): Boolean{
        var isValid = true;

        if(binding.exerciseName.text.toString().isEmpty()){
            isValid = false

            Toast.makeText(this, "Cant Save Without a Name", Toast.LENGTH_SHORT).show()
        }

        return isValid;
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}