package io.github.nirajprakash.patterns.ui.labs.contacts

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.nirajprakash.patterns.ui.base.ViewModelBase
import io.github.nirajprakash.patterns.ui.nav.NavManager
import javax.inject.Inject


/**
 * Created by Niraj on 03-11-2023.
 */
@HiltViewModel
class LabsContactsViewModel @Inject constructor(

    private val navManager: NavManager

): ViewModelBase() {



    fun navigateToContacts(){

    }


}