package com.anesabml.zalando.ui.productList

import com.anesabml.zalando.domain.model.Product

sealed class ProductListViewState {
    object Loading : ProductListViewState()
    data class Success(val data: List<Product>) : ProductListViewState()
    data class Error(val stringRes: Int) : ProductListViewState()
}