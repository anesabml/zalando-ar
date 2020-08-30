package com.anesabml.zalando.utils

import androidx.room.TypeConverter
import com.anesabml.zalando.domain.model.ProductCategory

class ProductCategoryConverter {

    @TypeConverter
    fun fromString(category: String): ProductCategory =
        ProductCategory.valueOf(category)

    @TypeConverter
    fun stringToProductCategory(category: ProductCategory): String =
        category.name
}