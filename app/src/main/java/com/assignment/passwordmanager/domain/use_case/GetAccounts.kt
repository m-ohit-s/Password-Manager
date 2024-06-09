package com.assignment.passwordmanager.domain.use_case

import com.assignment.passwordmanager.domain.model.Account
import com.assignment.passwordmanager.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

class GetAccounts(
    private val repository: AccountRepository
) {
    operator fun invoke(): Flow<List<Account>> {
        return repository.getAccounts()
    }
}