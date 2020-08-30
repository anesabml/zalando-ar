package com.anesabml.zalando.ui.productList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.anesabml.zalando.R
import com.anesabml.zalando.databinding.FragmentProductsBinding
import com.anesabml.zalando.extensions.viewBinding
import com.google.android.material.tabs.TabLayoutMediator

class ProductsFragment : Fragment(R.layout.fragment_products) {

    private val binding by viewBinding(FragmentProductsBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
    }

    private fun setupViewPager() {
        with(binding) {
            viewPager.adapter = ProductsPagerAdapter(
                childFragmentManager,
                lifecycle
            )

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.setText(R.string.label_new)
                    1 -> tab.setText(R.string.label_men)
                    2 -> tab.setText(R.string.label_women)
                }
            }.attach()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProductsFragment()
    }
}
