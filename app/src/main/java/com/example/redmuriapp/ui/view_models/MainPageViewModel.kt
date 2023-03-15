package com.example.redmuriapp.ui.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.redmuriapp.data.db.models.UserDbModel
import com.example.redmuriapp.domain.exceptions.UserNotFoundException
import com.example.redmuriapp.data.db.UsersRepositoryImpl
import com.example.redmuriapp.data.network.models.FlashSaleItemDto
import com.example.redmuriapp.data.network.ItemsRepositoryImpl
import com.example.redmuriapp.data.network.models.LatestItemDto
import com.example.redmuriapp.ui.states.MainError
import com.example.redmuriapp.ui.states.MainPageState
import com.example.redmuriapp.ui.states.MainProgress
import com.example.redmuriapp.ui.states.MainSuccess
import kotlinx.coroutines.*

class MainPageViewModel(application: Application) : AndroidViewModel(application) {

    private val usersRepositoryImpl = UsersRepositoryImpl(application)
    private val itemsRepositoryImpl = ItemsRepositoryImpl()

    private val handler = CoroutineExceptionHandler { _, _ ->
        _mainPageState.value = MainError(2)
    }
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate + handler)


    private var _mainPageState = MutableLiveData<MainPageState>()
    val mainPageState: LiveData<MainPageState> = _mainPageState

    private var _userData = MutableLiveData<UserDbModel>()
    val userData: LiveData<UserDbModel> = _userData

    private var _bothItems = MutableLiveData<Pair<List<LatestItemDto>, List<FlashSaleItemDto>>>()
    val bothItems: LiveData<Pair<List<LatestItemDto>, List<FlashSaleItemDto>>> = _bothItems

    fun getUser(firstName: String?) {
        scope.launch {
            try {
                if (firstName != null) {
                    _userData.value = usersRepositoryImpl.getUser(firstName)
                } else {
                    throw UserNotFoundException()
                }
            } catch (e: UserNotFoundException) {
                _mainPageState.value = MainError(ERROR_USER_NOT_FOUND)
            }
        }
    }

    fun getBothItems() {
        scope.launch() {
            _mainPageState.value = MainProgress
            try {
                val latestItemsDeferred = async { itemsRepositoryImpl.getLatestItems() }
                val flashSaleItemsDeferred = async { itemsRepositoryImpl.getFlashSaleItems() }
                val latestItems = latestItemsDeferred.await()
                val flashSaleItems = flashSaleItemsDeferred.await()
                _bothItems.value = Pair(latestItems, flashSaleItems)
                _mainPageState.value = MainSuccess
            } catch (e: Exception) {
                _mainPageState.value = MainError(ERROR_LOADING_ITEMS)
            }
        }
    }

    companion object {

        const val ERROR_USER_NOT_FOUND = 1
        const val ERROR_LOADING_ITEMS = 2
    }
}