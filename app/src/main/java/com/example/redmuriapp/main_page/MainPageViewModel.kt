package com.example.redmuriapp.main_page

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.redmuriapp.db.UserDbModel
import com.example.redmuriapp.db.UserNotFoundException
import com.example.redmuriapp.db.UsersRepositoryImpl
import kotlinx.coroutines.launch

class MainPageViewModel(application: Application) : AndroidViewModel(application) {

    private val usersRepositoryImpl = UsersRepositoryImpl(application)

    private var _mainPageState = MutableLiveData<MainPageState>()
    val mainPageState: LiveData<MainPageState> = _mainPageState

    private var _userData = MutableLiveData<UserDbModel>()
    val userData: LiveData<UserDbModel> = _userData

    fun getUser(firstName: String?) {
        viewModelScope.launch {
            try {
                if (firstName != null) {
                    _userData.value = usersRepositoryImpl.getUser(firstName)
                } else {
                    throw UserNotFoundException()
                }
            } catch (e: UserNotFoundException) {
                _mainPageState.value = Error(USER_NOT_FOUND)
            }
        }
    }

    companion object {

        const val USER_NOT_FOUND = 1
    }
}