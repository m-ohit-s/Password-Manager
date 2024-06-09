package com.assignment.passwordmanager.domain.use_case

data class AccountUseCases(
    val getAccounts: GetAccounts,
    val deleteAccount: DeleteAccount,
    val addAccount: AddAccount,
    val getAccountById: GetAccountById
)