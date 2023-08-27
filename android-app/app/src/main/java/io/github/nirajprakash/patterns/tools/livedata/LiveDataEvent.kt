package io.github.nirajprakash.patterns.tools.livedata

import android.util.Log

/**
 * Created by Niraj on 27-08-2023.
 */
class LiveDataEvent<out T>(private val data: T) {

    var isConsumed = false
    private set


    fun data(): T = data

    fun dataIfNotConsumed(): T? {
//        Log.d("LiveDataEvent", "isConsumed: $isConsumed")
        return if (isConsumed) {
            null
        } else {
            isConsumed = true
            data
        }
    }
}