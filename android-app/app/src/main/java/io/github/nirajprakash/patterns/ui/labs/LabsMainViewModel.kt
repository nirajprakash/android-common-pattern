package io.github.nirajprakash.patterns.ui.labs

import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun navigateToApp() {
//        _mEventNavigate.postValue(LiveDataEvent(LabsConstants.Navigation.NAV_APP))
        navManager.navigate(LabsMainFragmentDirections.navigateToNavApp())

    }

    fun navigateToGarbage(){

        navManager.navigate(LabsMainFragmentDirections.navigateToNavGarbage())
    }
}