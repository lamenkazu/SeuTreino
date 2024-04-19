package com.example.seutreino.model.repositories.interface_repository

import com.example.seutreino.model.entities.Workout
import com.example.seutreino.util.UiState

interface IWorkoutsRepository {

    fun getWorkouts(): UiState<List<Workout>>

}