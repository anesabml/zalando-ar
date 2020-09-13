package com.anesabml.zalandoar.ui.productList

import com.anesabml.zalandoar.domain.model.Product

sealed class ProductListViewState {
    object Loading : ProductListViewState()
    data class Success(val data: List<Product>) : ProductListViewState()
    data class Error(val stringRes: Int) : ProductListViewState()
}