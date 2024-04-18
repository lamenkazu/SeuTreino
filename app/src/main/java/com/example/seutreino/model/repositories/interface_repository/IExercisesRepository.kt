package com.example.seutreino.model.repositories.interface_repository

import com.example.seutreino.model.entities.Exercise

interface IExercisesRepository {

    fun getExercises(): List<Exercise>

}