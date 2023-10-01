package io.github.nirajprakash.patterns.di

import android.accounts.AccountManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.nirajprakash.patterns.R
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Niraj on 01-10-2023.
 */

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {

    const val ACCOUNT_AUTHENTICATOR_TYPE = "AccountModule.accountAuthenticatorType"

    @Provides
    @Named(ACCOUNT_AUTHENTICATOR_TYPE)
    fun provideAccountType(@ApplicationContext context: Context) = context.getString(R.string.account_type)

    @Singleton
    @Provides
    fun provideAccountManager(@ApplicationContext context: Context) = AccountManager.get(context)

    /*
    @EntryPoint
    @InstallIn(ServiceComponent::class)
    interface AccountAuthenticatorEntryPoint {
        fun get(accountManager: AccountManager): AppAuthenticator
    }
    */





}