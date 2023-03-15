package com.example.redmuriapp.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redmuriapp.data.db.repositories.UsersRepositoryImpl
import com.example.redmuriapp.domain.exceptions.UserDoesNotExistsException
import com.example.redmuriapp.domain.exceptions.WrongPasswordLogInException
import com.example.redmuriapp.domain.use_cases.LogInUseCase
import com.example.redmuriapp.ui.states.AuthError
import com.example.redmuriapp.ui.states.AuthProgress
import com.example.redmuriapp.ui.states.AuthState
import com.example.redmuriapp.ui.states.AuthSuccess
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
) : ViewModel() {

    private var _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun logIn(firstName: String, password: String) {
        val fieldsValid = validateInputLogIn(firstName, password)
        if (fieldsValid) {
            _authState.value = AuthProgress
            viewModelScope.launch {
                try {
                    logInUseCase.invoke(firstName,password) {
                        _authState.value = AuthSuccess(firstName)
                    }
                } catch (e: UserDoesNotExistsException){
                    _authState.value = AuthError(ERROR_USER_DOES_NOT_EXISTS)
                } catch (e: WrongPasswordLogInException){
                    _authState.value = AuthError(ERROR_WRONG_PASSWORD)
                }
            }
        }
    }

    private fun validateInputLogIn(firstName: String, password: String): Boolean {
        if (firstName.isBlank()) {
            _authState.value = AuthError(ERROR_EMPTY_FIRST_NAME)
            return false
        }
        if (password.isBlank()) {
            _authState.value = AuthError(ERROR_EMPTY_PASSWORD)
            return false
        }
        return true
    }

    companion object {

        const val ERROR_EMPTY_FIRST_NAME = 1
        const val ERROR_EMPTY_PASSWORD = 2
        const val ERROR_USER_DOES_NOT_EXISTS = 3
        const val ERROR_WRONG_PASSWORD = 4
    }
}