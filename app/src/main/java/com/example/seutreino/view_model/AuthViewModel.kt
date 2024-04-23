package com.example.seutreino.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seutreino.model.entities.User
import com.example.seutreino.model.repositories.interface_repository.IAuthRepository
import com.example.seutreino.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: IAuthRepository
): ViewModel() {

    private val _register = MutableLiveData<UiState<String>>()
    val register: LiveData<UiState<String>> get() = _register

    fun register(email: String, password: String, user: User){
        _register.value = UiState.Loading

        repository.signUpUser(email, password, user){
            _register.value = it
        }
    }

}