package com.example.seutreino.model.repository

import com.example.seutreino.model.entities.Exercise

interface IExercisesRepository {

    fun getExercises(): List<Exercise>

}