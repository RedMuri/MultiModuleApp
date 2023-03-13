package com.example.redmuriapp.authorization

import android.app.Application
import androidx.lifecycle.*
import com.example.redmuriapp.db.UsersRepositoryImpl
import com.example.redmuriapp.db.UserDoesNotExistsException
import com.example.redmuriapp.db.WrongPasswordLogInException
import kotlinx.coroutines.launch

class LogInViewModel(application: Application) : AndroidViewModel(application) {

    private val usersRepositoryImpl = UsersRepositoryImpl(application)

    private var _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun logIn(firstName: String, password: String) {
        val fieldsValid = validateInputLogIn(firstName, password)
        if (fieldsValid) {
            _authState.value = AuthProgress
            viewModelScope.launch {
                try {
                    usersRepositoryImpl.logIn(firstName,password) {
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