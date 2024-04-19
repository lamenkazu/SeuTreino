package com.example.seutreino.model.repositories.firebase_repository

import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.model.repositories.interface_repository.IExercisesRepository
import com.example.seutreino.util.FirestoreTables
import com.example.seutreino.util.UiState
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseExercisesRepository(
    val database: FirebaseFirestore
): IExercisesRepository {
    override fun getExercises(result: (UiState<List<Exercise>>) -> Unit) {

        database.collection(FirestoreTables.EXERCISES)
            .get()
            .addOnSuccessListener {

                val exercises = arrayListOf<Exercise>()

                for(document in it){
                    val exercise = document.toObject(Exercise::class.java)
                    exercises.add(exercise)
                }

                result.invoke(
                    UiState.Success(exercises)
                )
            }
            .addOnFailureListener{
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun addExercise(exercise: Exercise, result: (UiState<String>) -> Unit) {
        database.collection(FirestoreTables.EXERCISES)
            .add(exercise)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success(it.id)
                )
            }
            .addOnFailureListener{
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }
}