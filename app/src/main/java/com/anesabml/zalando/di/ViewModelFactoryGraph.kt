package com.anesabml.zalando.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.anesabml.zalando.ui.productList.ProductListFragment
import com.anesabml.zalando.ui.productList.ProductListViewModel

interface ViewModelFactoryGraph {
    fun getProductListViewModelFactory(fragment: ProductListFragment): AbstractSavedStateViewModelFactory
}

@Suppress("UNCHECKED_CAST")
class BaseViewModelFactoryGraph(
    private val dataGraph: DataGraph
) : ViewModelFactoryGraph {
    override fun getProductListViewModelFactory(fragment: ProductListFragment): AbstractSavedStateViewModelFactory {
        return object : AbstractSavedStateViewModelFactory(fragment, fragment.arguments) {
            override fun <T : ViewModel?> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                return ProductListViewModel(handle, dataGraph.productRepository) as T
            }
        }
    }
}
