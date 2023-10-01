package io.github.nirajprakash.patterns.modules.account.models

import io.github.nirajprakash.patterns.modules.account.AccountConstants

/**
 * Created by Niraj on 01-10-2023.
 */
class ModelAccountUser (val accountName: String,
                        val username: String?,
                        var accessToken: String?,
                        var authState: Int = AccountConstants.User.STATE_AUTHORIZED){
}