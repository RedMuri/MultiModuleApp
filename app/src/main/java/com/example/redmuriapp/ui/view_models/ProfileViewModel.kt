package com.example.redmuriapp.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redmuriapp.domain.entities.User
import com.example.redmuriapp.domain.exceptions.UserNotFoundException
import com.example.redmuriapp.domain.use_cases.EditUserImageUseCase
import com.example.redmuriapp.domain.use_cases.GetUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val editUserImageUseCase: EditUserImageUseCase,
) : ViewModel() {

    private var _userData = MutableLiveData<User>()
    val userData: LiveData<User> = _userData

    fun getUser(firstName: String?) {
        viewModelScope.launch {
            try {
                if (firstName != null) {
                    _userData.value = getUserUseCase.invoke(firstName)
                } else {
                    throw UserNotFoundException()
                }
            } catch (_: UserNotFoundException) {}
        }
    }

    fun editUserImage(imageUrl: String) {
        viewModelScope.launch {
            try {
                val firstName = userData.value?.firstName
                if (firstName != null) {
                    editUserImageUseCase.invoke(firstName, imageUrl)
                }
            } catch (_: Exception) {}
        }
    }
}