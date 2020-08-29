package com.anesabml.zalando.ui.productList

import com.anesabml.zalando.domain.model.Product

fun interface ProductOnClickListener {
    fun onClick(product: Product)
}