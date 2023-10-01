package io.github.nirajprakash.patterns.modules.account

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.nirajprakash.patterns.di.AccountModule
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Niraj on 01-10-2023.
 */
class AccountAuthenticator  @Inject constructor(
    @ApplicationContext private val mContext: Context,
    @Named(AccountModule.ACCOUNT_AUTHENTICATOR_TYPE) val mAccountAuthenticatorType: String,
    private var mAccountManager: AccountManager,) : AbstractAccountAuthenticator(mContext) {
    override fun editProperties(
        response: AccountAuthenticatorResponse?,
        accountType: String?
    ): Bundle {
        return  bundleOf()
    }

    override fun addAccount(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {
        TODO("Not yet implemented")
    }

    override fun confirmCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        options: Bundle?
    ): Bundle {
        return  bundleOf()

    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {
        TODO("Not yet implemented")

    }

    override fun getAuthTokenLabel(authTokenType: String?): String {
        return ""
    }

    override fun updateCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {
        return bundleOf()

    }

    override fun hasFeatures(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        features: Array<out String>?
    ): Bundle {
       return bundleOf()
    }
}