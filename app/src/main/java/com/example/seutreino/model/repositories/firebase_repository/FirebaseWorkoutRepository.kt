package com.example.seutreino.model.repositories.firebase_repository

import com.example.seutreino.model.entities.Workout
import com.example.seutreino.model.repositories.interface_repository.IWorkoutsRepository
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class FirebaseWorkoutRepository(
    val database: FirebaseFirestore
): IWorkoutsRepository {
    override fun getWorkouts(): List<Workout> {
        return arrayListOf(
            Workout(
                id="123456",
                name="Superior",
                description="Trabalhar membros superiores",
                date=Date(),
                exercisesIds = arrayListOf("1", "2", "3")
            ),
            Workout(
                id="123456",
                name="Inferior",
                description="Trabalhar membros inferiores",
                date=Date(),
                exercisesIds = arrayListOf("1", "2", "3")
            ),
        )
    }
}