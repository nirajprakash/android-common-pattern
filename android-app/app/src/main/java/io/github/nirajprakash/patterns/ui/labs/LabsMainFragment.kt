package io.github.nirajprakash.patterns.ui.labs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.nirajprakash.patterns.databinding.LabsMainFragmentBinding
import io.github.nirajprakash.patterns.ui.base.FragmentBase

/**
 *Created by Niraj on 27/08/23
 */
@AndroidEntryPoint
class LabsMainFragment: FragmentBase(backEnabled = true) {

    private lateinit var vBinding: LabsMainFragmentBinding
    private val mViewModel: LabsMainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            LabsMainFragmentBinding.inflate(inflater, container, false).apply {
                bViewModel = mViewModel
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}