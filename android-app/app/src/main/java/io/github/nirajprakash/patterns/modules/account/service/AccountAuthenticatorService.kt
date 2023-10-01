package io.github.nirajprakash.patterns.modules.account.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.github.nirajprakash.patterns.modules.account.AccountAuthenticator
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Niraj on 01-10-2023.
 */
class AccountAuthenticatorService: Service() {
//    override fun onBind(intent: Intent?): IBinder? {
//        TODO("Not yet implemented")
//    }


    @Inject
    lateinit var authenticatorProvider: Provider<AccountAuthenticator>

//   @Inject lateinit var accountManager: AccountManager
///@Inject constructor(var appAuthenticator:AppAuthenticator)

//    @Inject val authenticatorProvider: Provider<AppAuthenticator>

    override fun onBind(p0: Intent?): IBinder? {
//        val appAuthenticator =  EntryPoints.get( this, AccountModule.AccountAuthenticatorEntryPoint::class.java).get()//authenticatorProvider.get()
        val appAuthenticator = authenticatorProvider.get()

//        EntryPointAccessors.fromApplication( this.applicationContext, AccountModule.AccountAuthenticatorEntryPoint::class.java).get()//authenticatorProvider.get()
//        info {"Authenticator service: $appAuthenticator"}
//        info {"Authenticator service: ${appAuthenticator.iBinder}"}
        return appAuthenticator.iBinder
    }
}