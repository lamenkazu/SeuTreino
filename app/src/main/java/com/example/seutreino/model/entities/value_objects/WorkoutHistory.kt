package com.example.seutreino.model.entities.value_objects

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class WorkoutHistory(
    val workoutId: String,
    @ServerTimestamp
    val startTime: Date,
    @ServerTimestamp
    val interruptedDate: Date? = null,
    @ServerTimestamp
    val finishedDate: Date? = null,
)
