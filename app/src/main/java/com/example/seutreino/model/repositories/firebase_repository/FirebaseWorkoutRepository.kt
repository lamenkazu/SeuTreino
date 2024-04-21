package com.example.seutreino.model.repositories.firebase_repository

import com.example.seutreino.model.entities.Workout
import com.example.seutreino.model.entities.value_objects.ExerciseWithDuration
import com.example.seutreino.model.repositories.interface_repository.IWorkoutsRepository
import com.example.seutreino.util.UiState
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class FirebaseWorkoutRepository(
    val database: FirebaseFirestore
): IWorkoutsRepository {
    override fun getWorkouts(): UiState<List<Workout>> {
        val data = arrayListOf(
            Workout(
                id="123456",
                name="Superior",
                description="Trabalhar membros superiores",
                exercises = arrayListOf(
                    ExerciseWithDuration(exerciseId = "1", durationInSeconds = 30),
                    ExerciseWithDuration(exerciseId = "2", durationInSeconds = 30),
                )
            ),
            Workout(
                id="123456",
                name="Inferior",
                description="Trabalhar membros inferiores",
                exercises = arrayListOf(
                    ExerciseWithDuration(exerciseId = "1", durationInSeconds = 30),
                    ExerciseWithDuration(exerciseId = "2", durationInSeconds = 30),
                )
            ),
        )

        return if(data.isNullOrEmpty()){
            UiState.Failure("Data is Empty")
        }else{
            UiState.Success(data)
        }
    }
}