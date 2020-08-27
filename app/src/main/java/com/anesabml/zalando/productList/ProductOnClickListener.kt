package com.anesabml.zalando.productList

import com.anesabml.zalando.domain.model.Product

fun interface ProductOnClickListener {
    fun onClick(product: Product)
}