package com.example.seutreino.model.entities

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Workout(
    val id: String,
    val name: String,
    val description: String,
    @ServerTimestamp
    val date: Date,
    val exercises: List<String>
)
