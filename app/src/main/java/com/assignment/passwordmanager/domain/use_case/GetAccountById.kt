package com.assignment.passwordmanager.domain.use_case

import com.assignment.passwordmanager.domain.model.Account
import com.assignment.passwordmanager.domain.repository.AccountRepository

class GetAccountById(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(id: Int): Account?{
        return repository.getAccountById(id)
    }
}