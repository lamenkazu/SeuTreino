package com.example.seutreino.model.repositories.firebase_repository

import com.example.seutreino.model.entities.Workout
import com.example.seutreino.model.repositories.interface_repository.IWorkoutsRepository
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseWorkoutRepository(
    val database: FirebaseFirestore
): IWorkoutsRepository {
    override fun getWorkouts(): List<Workout> {
        TODO("Not yet implemented")
    }
}