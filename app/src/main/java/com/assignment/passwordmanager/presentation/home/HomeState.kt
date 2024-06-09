package com.assignment.passwordmanager.presentation.home

import com.assignment.passwordmanager.domain.model.Account

data class HomeState(
    val accounts: List<Account> = emptyList(),
    val isAddData: Boolean = false,
    val isEditData: Boolean = false,
    val isSheetOpen: Boolean = false,

    val currentId: Int? = null,
    val accountName: String = "",
    val username: String = "",
    val password: String = "",
    val isEditViewPasswordVisible: Boolean = false,
    val isAddViewPasswordVisible: Boolean = false
)
