package com.example.seutreino.model.in_memory_repository

import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.model.repository.IExercisesRepository

class InMemoryExercisesRepository: IExercisesRepository {
    override fun getExercises(): List<Exercise> {
        TODO("Not yet implemented")
    }
}