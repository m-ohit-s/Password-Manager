package com.assignment.passwordmanager.data.repository

import com.assignment.passwordmanager.data.source.AccountDao
import com.assignment.passwordmanager.domain.model.Account
import com.assignment.passwordmanager.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

class AccountRepositoryImpl(
    private val accountDao: AccountDao
): AccountRepository {
    override fun getAccounts(): Flow<List<Account>> {
        return accountDao.getAllAccounts()
    }

    override suspend fun getAccountById(id: Int): Account? {
        return accountDao.getAccountById(id)
    }

    override suspend fun insertAccount(account: Account) {
        return accountDao.insertAccount(account)
    }

    override suspend fun deleteAccount(account: Account) {
        return accountDao.deleteAccount(account)
    }
}