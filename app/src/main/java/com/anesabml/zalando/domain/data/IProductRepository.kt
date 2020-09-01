package com.anesabml.zalando.domain.data

import com.anesabml.zalando.domain.model.Product
import com.anesabml.zalando.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface IProductRepository {

    suspend fun getProducts(): Flow<List<Product>>

    suspend fun getProductsByCategory(category: ProductCategory): Flow<List<Product>>

    suspend fun getProductsSortedByTime(): Flow<List<Product>>

    suspend fun getProduct(productId: Int): Flow<Product>

    suspend fun updateProduct(product: Product)
}