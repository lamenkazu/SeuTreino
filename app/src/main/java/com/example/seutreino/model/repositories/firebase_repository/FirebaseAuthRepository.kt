package com.example.seutreino.model.repositories.firebase_repository

import com.example.seutreino.model.entities.Exercise
import com.example.seutreino.model.entities.User
import com.example.seutreino.model.repositories.interface_repository.IAuthRepository
import com.example.seutreino.model.repositories.interface_repository.IExercisesRepository
import com.example.seutreino.util.FirestoreTables
import com.example.seutreino.util.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseAuthRepository(
    private val database: FirebaseFirestore,
    private val auth: FirebaseAuth
): IAuthRepository {

    override fun signUpUser(email: String, password: String, user: User, result: (UiState<String>) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() {
                if(it.isSuccessful){

                    updateUserInfo(user){state ->
                        when(state){

                            is UiState.Loading -> {}

                            is UiState.Success -> {
                                result.invoke(
                                    UiState.Success("User registered successfully.")
                                )
                            }

                            is UiState.Failure -> {
                                result.invoke(
                                    UiState.Failure(state.error)
                                )
                            }

                        }
                    }



                }else{

                    try{
                        throw it.exception ?: java.lang.Exception("Invalid Authentication")

                    }catch (e: FirebaseAuthWeakPasswordException) {
                        result.invoke(UiState.Failure("Authentication failed, Password should be at least 6 characters"))
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        result.invoke(UiState.Failure("Authentication failed, Invalid email entered"))
                    } catch (e: FirebaseAuthUserCollisionException) {
                        result.invoke(UiState.Failure("Authentication failed, Email already registered."))
                    } catch (e: Exception) {
                        result.invoke(UiState.Failure(e.message))
                    }

                }
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(it.localizedMessage)
                )
            }
    }

    override fun updateUserInfo(user: User, result: (UiState<String>) -> Unit) {
        val document = database.collection(FirestoreTables.USERS).document()
        user.id = document.id

        document.set(user)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("User has been updated.")
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

    override fun signInUser(email: String, password: String, result: (UiState<String>) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    result.invoke(
                        UiState.Success(
                            "Login Successfull!"
                        )
                    )
                }else{
                    result.invoke(
                        UiState.Failure(
                            "Authentication Failed, check email and password."
                        )
                    )
                }
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun forgotPassword(user: User, result: (UiState<String>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun signOutUser(result: () -> Unit) {
        auth.signOut()
        result.invoke()
    }

}