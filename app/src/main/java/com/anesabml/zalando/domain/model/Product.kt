package com.anesabml.zalando.domain.model

import com.anesabml.zalando.utils.Differentiable

data class Product(
    val id: Int,
    val images: List<String>,
    val name: String,
    val description: String,
    val category: String,
    val price: Float,
    val availableSizes: List<String>,
    val availableColors: List<String>,
    val isBookmarked: Boolean,
) : Differentiable {
    override val diffId: Int
        get() = id
}