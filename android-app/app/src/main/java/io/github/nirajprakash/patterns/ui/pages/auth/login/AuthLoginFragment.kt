package io.github.nirajprakash.patterns.ui.pages.auth.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import io.github.nirajprakash.patterns.databinding.AuthLoginFragmentBinding
import io.github.nirajprakash.patterns.ui.base.FragmentBase

/**
 * Created by Niraj on 07-10-2023.
 */
@AndroidEntryPoint
class AuthLoginFragment:  FragmentBase(backEnabled = true) {
    private lateinit var vBinding: AuthLoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            AuthLoginFragmentBinding.inflate(inflater, container, false).apply {
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vBinding.lifecycleOwner = this.viewLifecycleOwner

        Log.d("AuthLoginFragment: ", "onViewCreated " )
    }
}