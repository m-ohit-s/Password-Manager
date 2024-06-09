package com.assignment.passwordmanager.domain.use_case

import com.assignment.passwordmanager.domain.model.Account
import com.assignment.passwordmanager.domain.model.InvalidDataFieldException
import com.assignment.passwordmanager.domain.repository.AccountRepository

class AddAccount(
    private val repository: AccountRepository
) {
    @Throws(InvalidDataFieldException::class)
    suspend operator fun invoke(account: Account){
        if(account.username.isBlank()){
            throw InvalidDataFieldException("username cannot be empty")
        }
        if(account.accountName.isBlank()){
            throw InvalidDataFieldException("account name cannot be empty")
        }
        if(account.password.isEmpty()){
            throw InvalidDataFieldException("password cannot be empty")
        }
        repository.insertAccount(account)
    }
}