package com.assignment.passwordmanager.presentation.home

import com.assignment.passwordmanager.domain.model.Account

sealed class HomeEvent {
    object AddEditData: HomeEvent()
    object DeleteData: HomeEvent()
    data class OpenEditDataSheet(val account: Account): HomeEvent()
    object OpenAddDataSheet: HomeEvent()
    object CloseDataSheet: HomeEvent()

    data class AccountNameChanged(val accountName: String): HomeEvent()
    data class PasswordChanged(val password: String): HomeEvent()
    data class UsernameChanged(val username: String): HomeEvent()

    object ToggleEditViewPasswordVisibility: HomeEvent()
    object ToggleAddViewPasswordVisibility: HomeEvent()
}