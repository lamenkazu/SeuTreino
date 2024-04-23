package com.example.seutreino.view.workout

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seutreino.R
import com.example.seutreino.databinding.ActivityStartWorkoutBinding
import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.model.entities.Workout
import com.example.seutreino.model.entities.value_objects.ExerciseWithDuration
import com.example.seutreino.util.UiState
import com.example.seutreino.util.hide
import com.example.seutreino.util.show
import com.example.seutreino.view_model.ExerciseViewModel
import com.example.seutreino.view_model.WorkoutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartWorkoutActivity : AppCompatActivity() {

    val TAG: String = "StartWorkoutActivity"

    private lateinit var binding: ActivityStartWorkoutBinding

    private val viewModel: WorkoutViewModel by viewModels()
    private val exerciseViewModel: ExerciseViewModel by viewModels()

    private val adapter by lazy{
        WorkoutExercisesListAdapter(
            onItemSelectionChanged = {exerciseId, isSelected ->},
        )
    }

    private lateinit var id: String
    private var isWorkoutStarted = false
    private lateinit var countdownTimer: CountDownTimer

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStartWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        defineActionBar()

        getWorkout()
    }

    private fun defineActionBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title = ""
    }

    private fun getWorkout(){

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        id = intent.extras?.getString("WorkoutId")!!

        viewModel.getWorkoutById(id)

        viewModel.workout.observe(this) { state ->
            when (state) {
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

                    binding.title.text = state.data.name

                    getExercises(state)


                }
            }
        }


    }

    private fun getExercises(state: UiState.Success<Workout>) {
        exerciseViewModel.getExercises()

        exerciseViewModel.exercises.observe(this){ exercise ->
            when(exercise){
                is UiState.Loading -> {
                    binding.progressBar.show()

                }

                is UiState.Failure -> {
                    binding.progressBar.hide()
                    Toast.makeText(this, exercise.error.toString(), Toast.LENGTH_SHORT).show()
                }

                is UiState.Success -> {
                    binding.progressBar.hide()
                    binding.startButton.show()

                    Log.d(TAG, exercise.data.toString())

                    setWorkoutList(state.data.exercises, exercise.data)

                    startWorkout()


                }
            }
        }
    }

    private fun setWorkoutList(exercisesWithDuration: List<ExerciseWithDuration>, exercises: List<Exercise>) {

        val exerciseNamesMap: MutableMap<String, String> = mutableMapOf()
        val exercisesWithDurationList = mutableListOf<ExerciseWithDuration>()

        for(exerciseWithDuration in exercisesWithDuration){
            for(exercise in exercises){

                if(exerciseWithDuration.exerciseId == exercise.id){
                    exerciseNamesMap[exerciseWithDuration.exerciseId] = exercise.name

                    exercisesWithDurationList.add(
                        ExerciseWithDuration(
                            exerciseId = exerciseWithDuration.exerciseId,
                            durationInSeconds = exerciseWithDuration.durationInSeconds
                        )
                    )
                }

            }

        }

        adapter.setList(exercisesWithDurationList, exerciseNamesMap)
    }

    private fun startWorkout() {
        binding.startButton.setOnClickListener {
            if (!isWorkoutStarted) {
                isWorkoutStarted = true
                binding.pauseButton.show()
                binding.startButton.text = getString(R.string.interrupt_label)
                binding.startButton.setBackgroundColor(Color.RED)
                binding.exerciseTimeMeasureLabel.show()

                val currentExercise = adapter.getNextExercise()
                if (currentExercise != null) {
                    startCountdown(currentExercise.durationInSeconds * 1000L, currentExercise)
                }
            } else {
                isWorkoutStarted = false

                onSupportNavigateUp()
            }
        }

        binding.pauseButton.setOnClickListener {
            isWorkoutStarted = false
            binding.pauseButton.hide()
            binding.startButton.text = getString(R.string.start_label)
            binding.startButton.setBackgroundColor(getColor(R.color.green))
            binding.exerciseNameLabel.text = ""
            binding.exerciseTimeLabel.text = ""
            binding.exerciseTimeMeasureLabel.hide()
            countdownTimer.cancel()
        }
    }

    private fun startCountdown(durationInMillis: Long, currentExercise: ExerciseWithDuration) {
        countdownTimer = object : CountDownTimer(durationInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val remainingSeconds = millisUntilFinished / 1000

                val minutes = remainingSeconds / 60
                val seconds = remainingSeconds % 60

                val formattedTime = String.format("%02d:%02d", minutes, seconds)

                binding.exerciseTimeLabel.text = formattedTime
                binding.exerciseNameLabel.text = adapter.getExerciseName(currentExercise.exerciseId)
            }

            override fun onFinish() {
                // Marcar o exercício como completo e atualizar a UI
                adapter.markExerciseComplete(currentExercise.exerciseId)

                //Procura próximo exercício para começar o countdown.
                val nextExerciseIndex = adapter.list.indexOf(currentExercise) + 1
                if (nextExerciseIndex < adapter.list.size) {
                    val nextExercise = adapter.list[nextExerciseIndex]
                    startCountdown(nextExercise.durationInSeconds * 1000L, nextExercise)

                } else {  // Todos os exercícios foram completos.

                    Toast.makeText(this@StartWorkoutActivity, "Workout completed!", Toast.LENGTH_SHORT).show()
                    isWorkoutStarted = false
                    binding.startButton.text = getString(R.string.start_label)
                    binding.startButton.setBackgroundColor(getColor(R.color.green))
                    binding.exerciseTimeLabel.text = ""
                    binding.exerciseNameLabel.text = ""
                    binding.exerciseTimeMeasureLabel.hide()
                    binding.pauseButton.hide()
                    binding.startButton.hide()
                    countdownTimer.cancel()
                }
            }
        }

        countdownTimer.start()
    }


}