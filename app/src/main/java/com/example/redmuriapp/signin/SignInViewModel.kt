package com.example.redmuriapp.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private var _signInState = MutableLiveData<SignInState>()
    val signInState: LiveData<SignInState> = _signInState

    fun signIn(firstName: String, lastName: String, email: String) {
        val fieldsValid = validateInputSignIn(firstName, lastName, email)
        if (fieldsValid) {
            _signInState.value = Progress
            // Loading
            viewModelScope.launch {
                delay(3000)
                _signInState.value = Success
            }
        }
    }

    private fun validateInputSignIn(firstName: String, lastName: String, email: String, ): Boolean {
        if (firstName.isBlank()) {
            _signInState.value = Error(ERROR_EMPTY_FIRST_NAME)
            return false
        }
        if (lastName.isBlank()) {
            _signInState.value = Error(ERROR_EMPTY_LAST_NAME)
            return false
        }
        if (email.isBlank()) {
            _signInState.value = Error(ERROR_EMPTY_EMAIL)
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _signInState.value = Error(ERROR_NOT_VALID_EMAIL)
            return false
        }
        return true
    }

    companion object {

        const val ERROR_EMPTY_FIRST_NAME = 1
        const val ERROR_EMPTY_LAST_NAME = 2
        const val ERROR_EMPTY_EMAIL = 3
        const val ERROR_NOT_VALID_EMAIL = 4
    }
}