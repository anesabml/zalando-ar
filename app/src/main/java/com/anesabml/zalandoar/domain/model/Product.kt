package com.anesabml.zalandoar.domain.model

import com.anesabml.zalandoar.utils.Differentiable
import java.util.Date

data class Product(
    val id: Int,
    val images: List<String>,
    val name: String,
    val description: String,
    val category: ProductCategory,
    val addedAt: Date,
    val price: Float,
    val availableSizes: List<String>,
    val availableColors: List<String>,
    val isBookmarked: Boolean,
) : Differentiable {
    override val diffId: Int
        get() = id
}