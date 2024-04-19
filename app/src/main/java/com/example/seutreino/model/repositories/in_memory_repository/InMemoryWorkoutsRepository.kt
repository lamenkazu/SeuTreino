package com.example.seutreino.model.repositories.in_memory_repository

import com.example.seutreino.model.entities.Workout
import com.example.seutreino.model.repositories.interface_repository.IWorkoutsRepository
import com.example.seutreino.util.UiState


class InMemoryWorkoutsRepository: IWorkoutsRepository {
    override fun getWorkouts(): UiState<List<Workout>> {
        TODO("Not yet implemented")
    }

}