package com.assignment.passwordmanager.presentation.home.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.passwordmanager.core.security.CryptoManager
import com.assignment.passwordmanager.domain.model.Account
import com.assignment.passwordmanager.domain.model.InvalidDataFieldException
import com.assignment.passwordmanager.domain.use_case.AccountUseCases
import com.assignment.passwordmanager.presentation.home.HomeEvent
import com.assignment.passwordmanager.presentation.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases
): ViewModel() {

    private var getPasswordsJob: Job? = null
    init {
        getAllData()
    }
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val cryptoManager = CryptoManager()

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.AddEditData -> {
                viewModelScope.launch {
                    try {
                        val encryptedBytes = cryptoManager.encrypt(state.value.password)
                        accountUseCases.addAccount(
                            Account(
                                accountName = state.value.accountName,
                                username = state.value.username,
                                password = encryptedBytes,
                                timestamp = System.currentTimeMillis(),
                                id = state.value.currentId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveAccount)
                    }catch (e: InvalidDataFieldException){
                        _eventFlow.emit(UiEvent.ShowToast(message = e.message ?: "Error in Saving Note"))
                    }catch (e: Exception){
                        _eventFlow.emit(UiEvent.ShowToast(message = e.message ?: "Something went wrong"))
                    }
                }
            }
            is HomeEvent.DeleteData -> {
                viewModelScope.launch {
                    state.value.currentId?.also {
                        accountUseCases.getAccountById(it)?.also {
                            accountUseCases.deleteAccount(it)
                        }
                    }
                }
            }

            is HomeEvent.OpenAddDataSheet -> {
                _state.value = _state.value.copy(isAddData = true, isSheetOpen = true, isEditData = false)
            }
            is HomeEvent.OpenEditDataSheet -> {
                _state.value = _state.value.copy(isEditData = true, isSheetOpen = true, isAddData = false)
                _state.value = _state.value.copy(
                    currentId = event.account.id,
                    username = event.account.username,
                    accountName = event.account.accountName,
                    password = cryptoManager.decrypt(event.account.password)
                )
            }

            is HomeEvent.AccountNameChanged -> {
                _state.value = _state.value.copy(accountName = event.accountName)
            }
            is HomeEvent.PasswordChanged -> {
                _state.value = _state.value.copy(password = event.password)
            }
            is HomeEvent.UsernameChanged -> {
                _state.value = _state.value.copy(username = event.username)
            }

            HomeEvent.CloseDataSheet -> {
                _state.value = _state.value.copy(
                    isSheetOpen = false,
                    isAddData = false,
                    isEditData = false,
                    accountName = "",
                    username = "",
                    password = "",
                    currentId = null,
                    isEditViewPasswordVisible = false,
                    isAddViewPasswordVisible = false
                )
            }

            is HomeEvent.ToggleEditViewPasswordVisibility -> {
                _state.value = _state.value.copy(isEditViewPasswordVisible = !state.value.isEditViewPasswordVisible)
            }

            is HomeEvent.ToggleAddViewPasswordVisibility -> {
                _state.value = _state.value.copy(isAddViewPasswordVisible = !state.value.isAddViewPasswordVisible)
            }
        }
    }
    private fun getAllData(){
        getPasswordsJob?.cancel()
        getPasswordsJob = accountUseCases.getAccounts()
            .onEach {
                accounts -> _state.value = state.value.copy(accounts = accounts)
            }.launchIn(viewModelScope)
    }

    sealed class UiEvent{
        data class ShowToast(val message: String): UiEvent()
        object SaveAccount: UiEvent()
    }
}