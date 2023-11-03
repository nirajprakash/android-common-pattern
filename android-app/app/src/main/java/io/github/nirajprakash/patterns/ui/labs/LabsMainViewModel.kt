package io.github.nirajprakash.patterns.ui.labs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.nirajprakash.patterns.tools.livedata.LiveDataEvent
import io.github.nirajprakash.patterns.ui.base.ViewModelBase
import io.github.nirajprakash.patterns.ui.nav.NavManager
import javax.inject.Inject

/**
 *Created by Niraj on 27/08/23
 */
@HiltViewModel
class LabsMainViewModel @Inject constructor(

    private val navManager: NavManager

): ViewModelBase() {


    private val _mEventStartAuth = MutableLiveData<LiveDataEvent<Boolean>>()

    val mEventStartAuth: LiveData<LiveDataEvent<Boolean>>
        get() = _mEventStartAuth


    fun navigateToApp() {
//        _mEventNavigate.postValue(LiveDataEvent(LabsConstants.Navigation.NAV_APP))
        navManager.navigate(LabsMainFragmentDirections.navigateToNavApp())

    }

    fun startAuth() {
        _mEventStartAuth.postValue(LiveDataEvent(true))

    }


    fun navigateToGarbage(){

        navManager.navigate(LabsMainFragmentDirections.navigateToNavGarbage())
    }

    fun navigateToContacts(){
        navManager.navigate(LabsMainFragmentDirections.navigateToLabsContacts())

    }
}