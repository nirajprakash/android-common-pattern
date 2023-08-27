package io.github.nirajprakash.patterns.ui.nav

import androidx.navigation.NavDirections
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Niraj on 27-08-2023.
 */
@Singleton
class NavManager @Inject constructor() {
    private var navEventListener: ((navDirections: NavDirections) -> Unit)? = null

    fun navigate(navDirections: NavDirections) {
        navEventListener?.invoke(navDirections)
    }

    fun setOnNavEvent(navEventListener: (navDirections: NavDirections) -> Unit) {
        this.navEventListener = navEventListener
    }
}