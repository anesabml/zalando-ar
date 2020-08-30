package com.anesabml.zalando.ui.splash

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

    override fun onResume() {
        super.onResume()

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.motionSplash.setTransitionListener(
            object : MotionLayout.TransitionListener {
                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    findNavController().navigate(R.id.productsFragment)
                }

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}

                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) {
                }
            }
        )
    }

    override fun onStop() {
        super.onStop()

        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

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