package com.anesabml.zalando.ui.productList

import com.anesabml.zalando.domain.model.Product

interface ProductInteractionsListener {
    fun onClick(product: Product)
}