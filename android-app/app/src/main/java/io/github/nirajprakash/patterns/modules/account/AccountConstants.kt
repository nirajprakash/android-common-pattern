package io.github.nirajprakash.patterns.modules.account

/**
 * Created by Niraj on 01-10-2023.
 */
object AccountConstants {
    object AccountResult {
        const val CODE_OK = 200
        const val CODE_FAILED = 400

        object Arguments {

            const val CODE = "accountResult.code"

            //            val FAILURE_MESSAGE = "account.failureMessage"
            const val MESSAGE = "accountResult.message"
        }
    }

    object Token {
        const val ACCESS_TOKEN = "accessToken"

        object Arguments {
//            const val TOKEN_TYPE_ACCESS = "Full access";
        }
    }

    object User {
        const val STATE_NOT_EXIST = 100
        const val STATE_AUTHORIZED = 101
        const val STATE_REAUTH = 102

        object Arguments {
            const val ACCOUNT_NAME = "accountUser.accountName"
            const val USER_NAME = "accountUser.username"
            const val PASSWORD = "accountUser.password"
            const val AUTH_STATE = "accountUser.authState"

        }
    }

}