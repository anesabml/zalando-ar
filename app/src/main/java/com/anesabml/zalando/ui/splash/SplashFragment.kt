package com.anesabml.zalando.ui.splash

import androidx.fragment.app.Fragment
import com.anesabml.zalando.R
import com.anesabml.zalando.databinding.FragmentSplashBinding
import com.anesabml.zalando.extensions.viewBinding

/**
 * A simple [Fragment] subclass to show a splash screen when the app starts
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding: FragmentSplashBinding by viewBinding(FragmentSplashBinding::bind)

    companion object {
        /**
         * Use this factory method to create
         * a new instance of this fragment.
         */
        @JvmStatic
        fun newInstance() =
            SplashFragment()
    }
}