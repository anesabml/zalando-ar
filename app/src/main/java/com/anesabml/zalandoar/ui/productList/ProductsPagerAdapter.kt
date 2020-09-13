package com.anesabml.zalandoar.ui.productList

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.anesabml.zalandoar.domain.model.ProductCategory

class ProductsPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val items = arrayOf(ProductCategory.NEW, ProductCategory.MEN, ProductCategory.WOMEN)

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment =
        ProductListFragment.newInstance(items[position])
}
