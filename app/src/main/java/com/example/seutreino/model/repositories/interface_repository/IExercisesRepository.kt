package com.example.seutreino.model.repositories.interface_repository

import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.util.UiState

interface IExercisesRepository {
    fun getExercises(result: (UiState<List<Exercise>>) -> Unit)

    fun addExercise(exercise: Exercise, result: (UiState<String>) -> Unit)

}