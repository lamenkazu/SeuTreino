package com.example.seutreino.model.repositories.interface_repository

import com.example.seutreino.model.entities.Workout
import com.example.seutreino.util.UiState

interface IWorkoutsRepository {

    fun getWorkouts(result: (UiState<List<Workout>>) -> Unit)
    fun addExercise(workout: Workout, result: (UiState<String>) -> Unit)
    fun deleteExercise(workout: Workout, result: (UiState<String>) -> Unit)

}