package com.assignment.passwordmanager.core.di

import android.app.Application
import androidx.room.Room
import com.assignment.passwordmanager.data.repository.AccountRepositoryImpl
import com.assignment.passwordmanager.data.source.AccountDatabase
import com.assignment.passwordmanager.domain.repository.AccountRepository
import com.assignment.passwordmanager.domain.use_case.AddAccount
import com.assignment.passwordmanager.domain.use_case.DeleteAccount
import com.assignment.passwordmanager.domain.use_case.GetAccounts
import com.assignment.passwordmanager.domain.use_case.AccountUseCases
import com.assignment.passwordmanager.domain.use_case.GetAccountById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePasswordDatabase(app: Application): AccountDatabase{
        return Room.databaseBuilder(
            app,
            AccountDatabase::class.java,
            AccountDatabase.DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun providePasswordRepository(db: AccountDatabase): AccountRepository{
        return AccountRepositoryImpl(db.accountDao)
    }

    @Provides
    @Singleton
    fun providePasswordUseCases(repository: AccountRepository): AccountUseCases{
        return AccountUseCases(
            getAccounts = GetAccounts(repository),
            deleteAccount = DeleteAccount(repository),
            addAccount = AddAccount(repository),
            getAccountById = GetAccountById(repository)
        )
    }
}