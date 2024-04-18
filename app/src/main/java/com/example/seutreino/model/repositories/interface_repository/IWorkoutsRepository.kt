package com.example.seutreino.model.repositories.interface_repository

import com.example.seutreino.model.entities.Workout

interface IWorkoutsRepository {

    fun getWorkouts(): List<Workout>

}