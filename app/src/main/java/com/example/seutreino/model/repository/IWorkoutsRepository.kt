package com.example.seutreino.model.repository

import com.example.seutreino.model.entities.Workout

interface IWorkoutsRepository {

    fun getWorkouts(): List<Workout>

}