package io.github.nirajprakash.patterns.ui.labs.contacts

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.nirajprakash.patterns.databinding.LabsContactsFragmentBinding
import io.github.nirajprakash.patterns.ui.base.FragmentBase
import java.util.Arrays


/**
 * Created by Niraj on 03-11-2023.
 */
@AndroidEntryPoint
class LabsContactsFragment : FragmentBase(backEnabled = true) {
    private lateinit var vBinding: LabsContactsFragmentBinding

    private val mViewModel by viewModels<LabsContactsViewModel>()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("LabsContactsFragment", "Permission:  Granted")
                fetchWhatsAppContacts()

            } else {
//                Log.i("Permission: ", "Denied")
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            LabsContactsFragmentBinding.inflate(inflater, container, false).apply {

            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        mViewModel..
        onClickRequestPermission()
    }

    fun getPermissions() {
//        ActivityCompat.requestPermissions(
//            requireActivity(),
//            arrayOf(Manifest.permission.READ_CONTACTS),
//            1
//        )


    }


    fun onClickRequestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED -> {
//                layout.showSnackbar(
//                    view,
//                    getString(R.string.permission_granted),
//                    Snackbar.LENGTH_INDEFINITE,
//                    null
//                ) {}
                Log.d("LabsContactsFragment", "onClickRequestPermission: granted")
                fetchWhatsAppContacts()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.READ_CONTACTS
            ) -> {
//               showSnackbar(
//                    view,
//                    getString(R.string.permission_required),
//                    Snackbar.LENGTH_INDEFINITE,
//                    getString(R.string.ok)
//                ) {
//                    requestPermissionLauncher.launch(
//                        Manifest.permission.CAMERA
//                    )
//                }
            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.READ_CONTACTS
                )
            }
        }
    }


    /* **************************************************
     *                                                  Contacts
     */

    fun fetchWhatsAppContacts() {
        Log.d("LabsContactsFragment: ", "fetchWhatsAppContacts")
        var result = fetchWhatsAppContactsContract()
        Log.d("LabsContactsFragment: ", "fetchWhatsAppContacts 2")

        if (result == null) return
        Log.d("LabsContactsFragment: ", "fetchWhatsAppContacts 3 ${result.size}")

        vBinding.labsContactsFragmentText.setText(result.sortedBy { map -> map["ContactName"]  }.mapIndexed { index, map  -> "${index}:       ${map.toString()} \n\n" }
            .toTypedArray<String>().contentToString())

    }

    /**
     *
     * @return ArrayList<Map></Map><String></String>,String>>()
     */
    @SuppressLint("Range")
    private fun fetchWhatsAppContactsContract(): MutableList<Map<String, String>>? {
        val list = mutableListOf<Map<String, String>>()
        val projection = arrayOf(
            ContactsContract.Data.CONTACT_ID,
            ContactsContract.Data.MIMETYPE,
            "account_type",
            ContactsContract.Data.DATA1,
            ContactsContract.Data.PHOTO_URI
        )
        val selection = ContactsContract.Data.MIMETYPE + " =? and account_type=?"
        val selectionArgs = arrayOf(
            "vnd.android.cursor.item/vnd.com.whatsapp.profile",
            "com.whatsapp"
        )
        val cr: ContentResolver = requireActivity().getContentResolver()
        val c = cr.query(
            ContactsContract.Data.CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )
        while (c!!.moveToNext()) {
            val id = c.getString(c.getColumnIndex(ContactsContract.Data.CONTACT_ID))
            val numberW = c.getString(c.getColumnIndex(ContactsContract.Data.DATA1))
            val parts = numberW.split("@".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            val numberPhone = parts[0]
            val number = "Tel : + " + numberPhone.substring(0, 2) + " " + numberPhone.substring(
                2,
                numberPhone.length
            )
//            val image_uri = c.getString(c.getColumnIndex(ContactsContract.Contacts.PHOTO_URI))
//            photoList.add(image_uri)
            var name = ""
            val mCursor: Cursor? = requireActivity().contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                arrayOf<String>(ContactsContract.Contacts.DISPLAY_NAME),
                ContactsContract.Contacts._ID + " =?",
                arrayOf<String>(id),
                null
            )
            if (mCursor != null) {
                while (mCursor.moveToNext()) {
                    name =
                        mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                }
                mCursor.close()
            }

            list.add(pushData(name, number))
        }
        return list
    }


    private fun pushData(ContactName: String, ContactNumber: String): Map<String, String> {
        val item = mutableMapOf<String, String>()
        item["ContactName"] = ContactName
        item["ContactNumber"] = ContactNumber
        return item
    }

}