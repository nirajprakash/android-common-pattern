package io.github.nirajprakash.patterns.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.nirajprakash.patterns.tools.livedata.LiveDataEvent
import io.github.nirajprakash.patterns.ui.base.ViewModelBase
import javax.inject.Inject

/**
 * Created by Niraj on 07-10-2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor(): ViewModelBase() {

    private val _mEventStartAuthActivity = MutableLiveData<LiveDataEvent<Boolean>>()

    val mEventStartAuthActivity: LiveData<LiveDataEvent<Boolean>>
        get() = _mEventStartAuthActivity

    fun startAuth(){
        _mEventStartAuthActivity.postValue(LiveDataEvent(true))
    }
}