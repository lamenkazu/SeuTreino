package com.example.seutreino.model.in_memory_repository

import com.example.seutreino.model.entities.Workout
import com.example.seutreino.model.repository.IWorkoutsRepository


class InMemoryWorkoutsRepository: IWorkoutsRepository  {
    override fun getWorkouts(): List<Workout> {
        TODO("Not yet implemented")
    }

}