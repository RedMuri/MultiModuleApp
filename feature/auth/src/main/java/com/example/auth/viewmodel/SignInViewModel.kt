package com.example.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.AuthError
import com.example.auth.AuthProgress
import com.example.auth.AuthState
import com.example.auth.AuthSuccess
import com.example.data.exceptions.UserAlreadyExistsException
import com.example.data.model.User
import com.example.domain.use_cases.SignInUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel() {

    private var _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun signIn(firstName: String, lastName: String, email: String) {
        val fieldsValid = validateInputSignIn(firstName, lastName, email)
        if (fieldsValid) {
            _authState.value = AuthProgress
            val user = User(firstName, lastName, email)
            viewModelScope.launch {
                try {
                    signInUseCase.invoke(user) {
                        _authState.value = AuthSuccess(firstName)
                    }
                } catch (e: UserAlreadyExistsException) {
                    _authState.value = AuthError(ERROR_SUCH_USER_EXISTS)
                }
            }

        }
    }

    private fun validateInputSignIn(firstName: String, lastName: String, email: String): Boolean {
        if (firstName.isBlank()) {
            _authState.value = AuthError(ERROR_EMPTY_FIRST_NAME)
            return false
        }
        if (lastName.isBlank()) {
            _authState.value = AuthError(ERROR_EMPTY_LAST_NAME)
            return false
        }
        if (email.isBlank()) {
            _authState.value = AuthError(ERROR_EMPTY_EMAIL)
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _authState.value = AuthError(ERROR_NOT_VALID_EMAIL)
            return false
        }
        return true
    }

    companion object {

        const val ERROR_EMPTY_FIRST_NAME = 1
        const val ERROR_EMPTY_LAST_NAME = 2
        const val ERROR_EMPTY_EMAIL = 3
        const val ERROR_NOT_VALID_EMAIL = 4
        const val ERROR_SUCH_USER_EXISTS = 5
    }
}