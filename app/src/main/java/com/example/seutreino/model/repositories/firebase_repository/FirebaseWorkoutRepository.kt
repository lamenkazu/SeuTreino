package com.example.seutreino.model.repositories.firebase_repository

import com.example.seutreino.model.entities.Workout
import com.example.seutreino.model.entities.value_objects.ExerciseWithDuration
import com.example.seutreino.model.repositories.interface_repository.IWorkoutsRepository
import com.example.seutreino.util.FirestoreTables
import com.example.seutreino.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import java.util.Date

class FirebaseWorkoutRepository(
    private val database: FirebaseFirestore
): IWorkoutsRepository {
    override fun getWorkouts(result: (UiState<List<Workout>>) -> Unit){
        database.collection(FirestoreTables.WORKOUTS)
            .get()
            .addOnSuccessListener {
                val workouts = arrayListOf<Workout>()

                for(document in it){
                    val workout = document.toObject(Workout::class.java)
                    workouts.add(workout)
                }

                result.invoke(
                    UiState.Success(workouts)
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun addExercise(workout: Workout, result: (UiState<String>) -> Unit) {
        val document = database.collection(FirestoreTables.WORKOUTS).document()

        workout.id = document.id

        document.set(workout)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Workout has been created.")
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

    override fun deleteExercise(workout: Workout, result: (UiState<String>) -> Unit) {

        val document = database
            .collection(FirestoreTables.WORKOUTS)
            .document(workout.id)

        document.delete()
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Workout has been deleted.")
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }



    }
}