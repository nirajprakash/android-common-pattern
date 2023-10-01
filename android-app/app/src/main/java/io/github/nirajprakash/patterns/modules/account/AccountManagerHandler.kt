package io.github.nirajprakash.patterns.modules.account

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AuthenticatorException
import android.accounts.OperationCanceledException
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import io.github.nirajprakash.patterns.di.AccountModule
import io.github.nirajprakash.patterns.modules.account.models.ModelAccountUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Niraj on 01-10-2023.
 */
@Singleton
class AccountManagerHandler @Inject constructor(
    private val mAccountManager: AccountManager,
    @Named(AccountModule.ACCOUNT_AUTHENTICATOR_TYPE) val mAccountAuthenticatorType: String
) {

    fun requestAddAccount(
        activity: Activity, options: Bundle,
        callback: AccountManagerCallback<Bundle>?
    )  : Boolean {
//        info("Account Manager addAccount(): ${activity}, ${options}, ${callback}")
  var future =       mAccountManager.addAccount(
            mAccountAuthenticatorType, null, null, options, activity,
            callback, null
        )

        try {
            val b = future.result
//                info { "account manager callback: $b" }
            //warn(b.toString())

            var action = b.getInt(
                AccountConstants.AccountResult.Arguments.CODE,
                AccountConstants.AccountResult.CODE_FAILED
            )

            return action == AccountConstants.AccountResult.CODE_OK
//            _mAccountReAuthEvent.isReAuthActive.compareAndSet(true, false);
        } catch (e: OperationCanceledException) {
            //error{e.toString()}
            e.printStackTrace()

//            _mAccountReAuthEvent.isReAuthActive.compareAndSet(true, false);
//                    emitter.onError(e)
        } catch (e: IOException) {
            //error(e.toString())
            e.printStackTrace()

//            _mAccountReAuthEvent.isReAuthActive.compareAndSet(true, false);
        } catch (e: AuthenticatorException) {
            //error(e.toString())
            e.printStackTrace()

//            _mAccountReAuthEvent.isReAuthActive.compareAndSet(true, false);

        }
        // TODO throw exception
        return false
    }

    /* *****************************************************************************
     *                                                                                              account
     */
    fun hasAccount(): Boolean {
        val accounts = getAccounts()
        return accounts.isNotEmpty()
    }

    private fun getAccounts(): Array<Account> {
        return mAccountManager.getAccountsByType(mAccountAuthenticatorType)
    }

    private fun getAccountByName(accountName: String): Account? {
        val accounts = this.getAccounts()
        for (account in accounts) {
            if (accountName == account.name) {
                return account
            }
        }
        return null
    }

    fun createAccount(user: ModelAccountUser): Account? {
//        warn { "createaccount: $user" }
        var account = getAccountByName(user.accountName)
        if (account == null) {
            account = Account(user.accountName, mAccountAuthenticatorType)

            if (mAccountManager.addAccountExplicitly(account, null, null)) {
                //info( "Account created.")
//              TODO  saveRefreshToken(account, user.refreshToken)
//              TODO  use Credential
//                saveAccountUser(account, user)
//                saveToken(account, user.cookie)
                return account
            }
        } else {
            //              TODO  use Credential
//            saveAccountUser(account, user)
//            saveToken(account, user.cookie)
            return account
        }

        return null
    }


    /* ******************************************************************************
     *                                                                                  Token
     */


    suspend fun requestAuthToken(type: String?): String? = withContext(Dispatchers.IO) {

//        return@withContext ""
        val accounts = getAccounts()
        if (accounts.isEmpty()) {
            try {
                return@withContext null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

////            info { "www account: ${accounts[0].name}" }
////            info {"requestAuthToken: $accounts, $type"}
        var future = mAccountManager.getAuthToken(
            accounts[0],
            type ?: AccountConstants.Token.ACCESS_TOKEN,
            null,
            true,
            null,
            Handler(Looper.myLooper() ?: Looper.getMainLooper())
        )

        try {
//                        info {"requestAuthTokenCallback: $future"}
            val token: String? = future?.result
                ?.getString(AccountManager.KEY_AUTHTOKEN)

            return@withContext token

//                        info {"requestAuthTokenCallback: $token"}
//                    token?.let { st -> it.resume(st) }// exception occurs here
            // [...]
        } catch (e: Exception) {
//                        info {"Exception Caught"}
            e.printStackTrace()
            //                             Log.e("account", "exception occurs", e)
        }

        return@withContext null;
////            info ("End of requestAuthToken")

    }

}