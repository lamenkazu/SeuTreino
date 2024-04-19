package com.example.seutreino.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.model.repositories.interface_repository.IExercisesRepository
import com.example.seutreino.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val repository: IExercisesRepository
): ViewModel() {

    private val _exercises = MutableLiveData<UiState<List<Exercise>>>()
    val exercises: LiveData<UiState<List<Exercise>>> get() = _exercises

    private val _addExercise = MutableLiveData<UiState<String>>()
    val addExercise: LiveData<UiState<String>> get() = _addExercise


    fun getExercises(){
        _exercises.value = UiState.Loading

        repository.getExercises {
            _exercises.value = it
        }

    }

    fun addExercise(exercise: Exercise){
        _addExercise.value = UiState.Loading
        repository.addExercise(exercise){
            _addExercise.value = it
        }

    }

}