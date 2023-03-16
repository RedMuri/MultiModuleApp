package com.example.redmuriapp.ui.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redmuriapp.domain.entities.FlashSaleItem
import com.example.redmuriapp.domain.entities.LatestItem
import com.example.redmuriapp.domain.entities.User
import com.example.redmuriapp.domain.exceptions.UserNotFoundException
import com.example.redmuriapp.domain.use_cases.GetFlashSaleItemsUseCase
import com.example.redmuriapp.domain.use_cases.GetLatestItemsUseCase
import com.example.redmuriapp.domain.use_cases.GetUserUseCase
import com.example.redmuriapp.ui.states.MainError
import com.example.redmuriapp.ui.states.MainPageState
import com.example.redmuriapp.ui.states.MainProgress
import com.example.redmuriapp.ui.states.MainSuccess
import kotlinx.coroutines.*
import javax.inject.Inject

class MainPageViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getLatestItemsUseCase: GetLatestItemsUseCase,
    private val getFlashSaleItemsUseCase: GetFlashSaleItemsUseCase,
) : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, _ ->
        _mainPageState.value = MainError(2)
    }
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate + handler)

    private var _mainPageState = MutableLiveData<MainPageState>()
    val mainPageState: LiveData<MainPageState> = _mainPageState

    private var _userData = MutableLiveData<User>()
    val userData: LiveData<User> = _userData

    private var _bothItems = MutableLiveData<Pair<List<LatestItem>, List<FlashSaleItem>>>()
    val bothItems: LiveData<Pair<List<LatestItem>, List<FlashSaleItem>>> = _bothItems

    fun getUser(firstName: String?) {
        scope.launch {
            try {
                if (firstName != null) {
                    _userData.value = getUserUseCase.invoke(firstName)
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
                val latestItemsDeferred = async { getLatestItemsUseCase.invoke() }
                val flashSaleItemsDeferred = async { getFlashSaleItemsUseCase.invoke() }
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