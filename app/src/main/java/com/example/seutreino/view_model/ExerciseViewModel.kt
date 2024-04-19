package com.example.seutreino.view_model

import android.os.Looper
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

    fun getExercises(){
        _exercises.value = UiState.Loading

        //2 segundos para simular o delay de informações do BD
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            _exercises.value = repository.getExercises()
        }, 2000)

    }

}