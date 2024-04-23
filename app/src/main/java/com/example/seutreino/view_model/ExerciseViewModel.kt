package com.example.seutreino.view_model

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.model.repositories.interface_repository.IExercisesRepository
import com.example.seutreino.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val repository: IExercisesRepository
): ViewModel() {

    private val _exercises = MutableLiveData<UiState<List<Exercise>>>()
    val exercises: LiveData<UiState<List<Exercise>>> get() = _exercises

    private val _exercise = MutableLiveData<UiState<Exercise>>()
    val exercise: LiveData<UiState<Exercise>> get() = _exercise

    private val _addExercise = MutableLiveData<UiState<String>>()
    val addExercise: LiveData<UiState<String>> get() = _addExercise

    private val _updateExercise = MutableLiveData<UiState<String>>()
    val updateExercise: LiveData<UiState<String>> get() = _updateExercise

    private val _deleteExercise = MutableLiveData<UiState<String>>()
    val deleteExercise: LiveData<UiState<String>> get() = _deleteExercise


    fun getExercises(){
        _exercises.value = UiState.Loading

        repository.getExercises {
            _exercises.value = it
        }

    }

    fun getExerciseById(id: String){
        _exercise.value = UiState.Loading

        repository.getExerciseById(id){
            _exercise.value = it

        }

    }

    fun addExercise(exercise: Exercise, imageUrl: Uri){
        _addExercise.value = UiState.Loading
        repository.addExercise(exercise, imageUrl){
            _addExercise.value = it
        }

    }

    fun updateExercise(exercise: Exercise){
        _updateExercise.value = UiState.Loading
        repository.updateExercise(exercise){
            _updateExercise.value = it
        }

    }

    fun deleteExercise(exercise: Exercise) {
        _deleteExercise.value = UiState.Loading
        repository.deleteExercise(exercise){
            _deleteExercise.value = it
        }
    }

    fun onUploadSingleFile(fileUri: Uri, onResult: (UiState<Uri>) -> Unit){
        onResult.invoke(UiState.Loading)

        viewModelScope.launch {
            repository.uploadSingleFile(fileUri, onResult)
        }
    }

}