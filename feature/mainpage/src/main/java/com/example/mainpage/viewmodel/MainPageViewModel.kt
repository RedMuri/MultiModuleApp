package com.example.mainpage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.exceptions.UserNotFoundException
import com.example.data.model.FlashSaleItem
import com.example.data.model.LatestItem
import com.example.data.model.User
import com.example.domain.use_cases.GetFlashSaleItemsUseCase
import com.example.domain.use_cases.GetLatestItemsUseCase
import com.example.domain.use_cases.GetUserUseCase
import com.example.mainpage.MainError
import com.example.mainpage.MainPageState
import com.example.mainpage.MainProgress
import com.example.mainpage.MainSuccess
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