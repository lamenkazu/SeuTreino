package com.example.seutreino.model.entities

import com.example.seutreino.model.entities.value_objects.ExerciseWithDuration
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Workout(
    val id: String,
    val name: String,
    val description: String,
    val exercises: List<ExerciseWithDuration>
)
