package io.github.nirajprakash.patterns.ui.pages.launcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import io.github.nirajprakash.patterns.databinding.LauncherFragmentBinding
import io.github.nirajprakash.patterns.ui.base.FragmentBase

/**
 *Created by Niraj on 27/08/23
 */
@AndroidEntryPoint
class LauncherFragment: FragmentBase(backEnabled = true) {
    private lateinit var vBinding: LauncherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            LauncherFragmentBinding.inflate(inflater, container, false).apply {

            }
        return vBinding.root
    }


}