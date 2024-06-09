package com.assignment.passwordmanager.domain.use_case

import com.assignment.passwordmanager.domain.model.Account
import com.assignment.passwordmanager.domain.repository.AccountRepository

class DeleteAccount(
    private val repository: AccountRepository
){
    suspend operator fun invoke(account: Account){
        repository.deleteAccount(account)
    }
}