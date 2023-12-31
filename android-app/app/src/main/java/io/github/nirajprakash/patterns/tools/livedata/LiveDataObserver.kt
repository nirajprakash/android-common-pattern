package io.github.nirajprakash.patterns.tools.livedata

import android.util.Log
import androidx.lifecycle.Observer

/**
 * Created by Niraj on 28-08-2023.
 */
class LiveDataObserver<T>(private val onEvent: (T) -> Unit) : Observer<LiveDataEvent<T>> {
    override fun onChanged(event: LiveDataEvent<T>) {


        Log.d("LiveDataObserver", "onChanged: ")
        event.dataIfNotConsumed()?.let {
            onEvent(it)
        }
    }
}