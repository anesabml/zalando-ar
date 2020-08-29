package com.anesabml.zalando.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anesabml.zalando.ui.productList.ProductListViewModel

interface ViewModelFactoryGraph {
    fun getProductListViewModelFactory(): ViewModelProvider.Factory
}

@Suppress("UNCHECKED_CAST")
class BaseViewModelFactoryGraph(
    private val dataGraph: DataGraph
) : ViewModelFactoryGraph {
    override fun getProductListViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ProductListViewModel(dataGraph.productRepository) as T
            }
        }
    }
}
