package com.example.seutreino.model.repositories.interface_repository

import com.example.seutreino.model.entities.User
import com.example.seutreino.util.UiState

interface IAuthRepository {
    fun signUpUser(email: String, password: String, user: User, result: (UiState<String>) -> Unit)
    fun updateUserInfo(user: User, result: (UiState<String>) -> Unit)
    fun signInUser(email: String, password: String, result: (UiState<String>) -> Unit)
    fun forgotPassword(user: User, result: (UiState<String>) -> Unit)

}