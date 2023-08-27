package io.github.nirajprakash.patterns.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.nirajprakash.patterns.tools.livedata.LiveDataEvent

/**
 *Created by Niraj on 27/08/23
 */
abstract class ViewModelBase : ViewModel() {
    private val mSnackBarText = MutableLiveData<LiveDataEvent<String>>()
    val snackBarText: LiveData<LiveDataEvent<String>> = mSnackBarText
}