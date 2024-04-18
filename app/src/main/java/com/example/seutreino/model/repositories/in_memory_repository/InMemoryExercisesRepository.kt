package com.example.seutreino.model.repositories.in_memory_repository

import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.model.repositories.interface_repository.IExercisesRepository

class InMemoryExercisesRepository: IExercisesRepository {
    override fun getExercises(): List<Exercise> {
        TODO("Not yet implemented")
    }
}