package com.anesabml.zalando.ui.productDetails

import com.anesabml.zalando.domain.model.Product

sealed class ProductDetailsViewState {
    object Loading : ProductDetailsViewState()
    data class Success(val data: Product) : ProductDetailsViewState()
    data class Error(val message: String) : ProductDetailsViewState()
}
