package com.example.seutreino.view_model

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seutreino.model.entities.Workout
import com.example.seutreino.model.repositories.interface_repository.IWorkoutsRepository
import com.example.seutreino.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val repository: IWorkoutsRepository
): ViewModel() {
    private val _workouts = MutableLiveData<UiState<List<Workout>>>()
    val exercises: LiveData<UiState<List<Workout>>> get() = _workouts

    fun getWorkouts(){
        _workouts.value = UiState.Loading

        //2 segundos para simular o delay de informações do BD
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            _workouts.value = repository.getWorkouts()
        }, 2000)
    }
}