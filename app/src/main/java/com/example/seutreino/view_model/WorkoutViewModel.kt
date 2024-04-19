package com.example.seutreino.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seutreino.model.entities.Workout
import com.example.seutreino.model.repositories.interface_repository.IWorkoutsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val repository: IWorkoutsRepository
): ViewModel() {
    private val _workouts = MutableLiveData<List<Workout>>()
    val exercises: LiveData<List<Workout>> get() = _workouts

    fun getWorkouts(){
        _workouts.value = repository.getWorkouts()
    }
}