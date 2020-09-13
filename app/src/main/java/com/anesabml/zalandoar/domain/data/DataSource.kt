package com.anesabml.zalandoar.domain.data

import com.anesabml.zalandoar.data.entity.ProductEntity
import com.anesabml.zalandoar.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface DataSource {

    suspend fun insertAll(products: List<ProductEntity>)

    fun getProducts(): Flow<List<ProductEntity>>

    fun getProductsByCategory(category: ProductCategory): Flow<List<ProductEntity>>

    fun getProductsSortedByTime(): Flow<List<ProductEntity>>

    fun getProduct(productId: Int): Flow<ProductEntity>

    suspend fun updateProduct(productEntity: ProductEntity)
}