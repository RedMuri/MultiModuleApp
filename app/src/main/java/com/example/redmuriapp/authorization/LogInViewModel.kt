package com.example.redmuriapp.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {

    private var _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun logIn(firstName: String, password: String) {
        val fieldsValid = validateInputLogIn(firstName, password)
        if (fieldsValid) {
            _authState.value = Progress
            // Loading
            viewModelScope.launch {
                delay(3000)
                _authState.value = Success
            }
        }
    }

    private fun validateInputLogIn(firstName: String, password: String): Boolean {
        if (firstName.isBlank()) {
            _authState.value = Error(ERROR_EMPTY_FIRST_NAME)
            return false
        }
        if (password.isBlank()) {
            _authState.value = Error(ERROR_EMPTY_PASSWORD)
            return false
        }
        return true
    }

    companion object {

        const val ERROR_EMPTY_FIRST_NAME = 1
        const val ERROR_EMPTY_PASSWORD = 2
    }
}