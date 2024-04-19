package com.example.seutreino.model.repositories.in_memory_repository

import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.model.repositories.interface_repository.IExercisesRepository
import com.example.seutreino.util.UiState

class InMemoryExercisesRepository: IExercisesRepository {
    override fun getExercises(): UiState<List<Exercise>> {
        TODO("Not yet implemented")
    }
}