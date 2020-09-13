package com.anesabml.zalandoar.ui.productDetails

import com.anesabml.zalandoar.domain.model.Product

sealed class ProductDetailsViewState {
    object Loading : ProductDetailsViewState()
    data class Success(val data: Product) : ProductDetailsViewState()
    data class Error(val message: String) : ProductDetailsViewState()
}
