package com.example.seutreino.model.repositories.firebase_repository

import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.model.repositories.interface_repository.IExercisesRepository
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseExercisesRepository(
    val database: FirebaseFirestore
): IExercisesRepository {
    override fun getExercises(): List<Exercise> {
        return arrayListOf(
            Exercise(
                id="abcd",
                name="abdominal",
                image="link",
                observations="some info"
            ),
            Exercise(
                id="abcd",
                name="flexao",
                image="link",
                observations="some info"
            ),

        )
    }
}