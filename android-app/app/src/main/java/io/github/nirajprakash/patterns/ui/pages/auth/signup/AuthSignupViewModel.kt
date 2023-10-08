package io.github.nirajprakash.patterns.ui.pages.auth.signup

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.nirajprakash.patterns.ui.base.ViewModelBase
import javax.inject.Inject

/**
 * Created by Niraj on 08-10-2023.
 */
@HiltViewModel
class AuthSignupViewModel  @Inject constructor(): ViewModelBase(){


    val bUsername = MutableLiveData<String>()
    val bPassword = MutableLiveData<String>()
    val bFormIsValid = MediatorLiveData<Boolean>()
    val bIsInput =  MutableLiveData(false)


    val bIsInProgress = MutableLiveData(false)


    init {
        Log.d("AuthSignupViewmodel: ", "init model " )

        bFormIsValid.addSource(bUsername) {
            formEnable()
            Log.d("AuthSignupViewmodel", "bUsername: ")

        }



        bFormIsValid.addSource(bPassword) {
            formEnable()
        }


    }

    private fun formEnable() {
//        val isProfileDataChanged = mObPepStudent?.firstName != bFullName.value || mModelAccountUser?.contact != bPhoneNumber.value || mGrade != bGrade.value || mModelAccountUser?.email != bEmail.value
        var isUsernameValid = validateUsername(bUsername.value)
        var isPasswordValid = validateUsername(bPassword.value)
        bFormIsValid.value = (bUsername.value?.length ?: 0) > 0 && isUsernameValid && isPasswordValid

        Log.d("${javaClass::getSimpleName}", "formEnable: ")


    }

    private fun validateUsername(username: String?): Boolean {
        if (username == null || username.isEmpty()) {
            return false
        }
        return true
//        return android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }

    private fun validatePassword(password: String?): Boolean {
        if (password == null || password.isEmpty()) {
            return false
        }
        return true
//        return android.util.Patterns..matcher(username).matches()
    }
    fun bActionInput() {
        bIsInput.postValue(true)
    }

    fun bActionSubmit() {
/*
        val name = bName.value
        val emailId = bEmail.value
        var referralCode = bReferralCode.value

        var userCreateReq: ParamUserCreateReq = ParamUserCreateReq(name, emailId, true, referralCode)
        apiCreate(userCreateReq)
*/


        /*_mEventSubmit.postValue(
            LiveDataEvent(
                ParamPepReqUserProfile(
                    fullName,
                    emailId,
                    standardId,
                    contact,
                )
            )
        )*/
    }

}