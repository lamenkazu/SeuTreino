package com.example.seutreino.model.repositories.firebase_repository

import android.net.Uri
import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.model.repositories.interface_repository.IExercisesRepository
import com.example.seutreino.util.FirestoreTables
import com.example.seutreino.util.UiState
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseExercisesRepository(
    private val database: FirebaseFirestore,
    private val storageReference: StorageReference
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

    override fun getExerciseById(exerciseId: String, result: (UiState<Exercise>) -> Unit) {
        database.collection(FirestoreTables.EXERCISES)
            .document(exerciseId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val exercise = documentSnapshot.toObject(Exercise::class.java)
                    if (exercise != null) {
                        result.invoke(UiState.Success(exercise))
                    } else {
                        result.invoke(UiState.Failure("Error getting exercise data"))
                    }
                } else {
                    result.invoke(UiState.Failure("Exercise with ID $exerciseId not found"))
                }
            }
            .addOnFailureListener {
                result.invoke(UiState.Failure(it.localizedMessage))
            }


    }

    override fun addExercise(exercise: Exercise, imageUrl: Uri, result: (UiState<String>) -> Unit) {

        val document = database.collection(FirestoreTables.EXERCISES).document()

        exercise.image = imageUrl.toString()
        exercise.id = document.id

        document.set(exercise)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Exercise has been created.")
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

    override fun updateExercise(exercise: Exercise, result: (UiState<String>) -> Unit) {
        val document = database.collection(FirestoreTables.EXERCISES).document(exercise.id)

        document.set(exercise)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Exercise has been updated.")
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

    override fun deleteExercise(exercise: Exercise, result: (UiState<String>) -> Unit) {
        val document = database.collection(FirestoreTables.EXERCISES).document(exercise.id)

        document.delete()
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Exercise has been deleted.")
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

    override suspend fun uploadSingleFile(fileUri: Uri, onResult: (UiState<Uri>) -> Unit) {
        try {
            val uri: Uri = withContext(Dispatchers.IO) {
                storageReference.putFile(fileUri)
                    .await()
                    .storage
                    .downloadUrl
                    .await()
            }

            onResult.invoke(
                UiState.Success(
                    uri
                )
            )

        } catch (e: FirebaseException) {
            onResult.invoke(
                UiState.Failure(
                    e.message
                )
            )
        } catch (e: Exception) {
            onResult.invoke(
                UiState.Failure(
                    e.message
                )
            )
        }
    }
}