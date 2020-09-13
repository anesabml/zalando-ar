package com.anesabml.zalandoar.utils

import androidx.room.TypeConverter
import com.anesabml.zalandoar.domain.model.ProductCategory

class ProductCategoryConverter {

    @TypeConverter
    fun fromString(category: String): ProductCategory =
        ProductCategory.valueOf(category)

    @TypeConverter
    fun stringToProductCategory(category: ProductCategory): String =
        category.name
}