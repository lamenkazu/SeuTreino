package com.example.seutreino.model.entities

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class User(
    var id: String = "",
    val name: String = "",
    val email: String = ""
)
