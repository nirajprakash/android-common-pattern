package io.github.nirajprakash.patterns.ui.pages.auth.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.nirajprakash.patterns.databinding.AuthSignupFragmentBinding
import io.github.nirajprakash.patterns.tools.livedata.LiveDataObserver
import io.github.nirajprakash.patterns.ui.base.FragmentBase
import io.github.nirajprakash.patterns.ui.labs.LabsMainViewModel

/**
 * Created by Niraj on 08-10-2023.
 */
@AndroidEntryPoint
class AuthSignupFragment:  FragmentBase(backEnabled = true) {
    private lateinit var vBinding: AuthSignupFragmentBinding

    private val mViewModel: AuthSignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            AuthSignupFragmentBinding.inflate(inflater, container, false).apply {
                bViewModel = mViewModel
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vBinding.lifecycleOwner = this.viewLifecycleOwner

        Log.d("AuthSignupFragment: ", "onViewCreated " )
    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()

//        mViewModel.mEventStartAuth.observe(viewLifecycleOwner, LiveDataObserver{
//
//            mViewModelActivity.startAuth()
//        })
    }
}