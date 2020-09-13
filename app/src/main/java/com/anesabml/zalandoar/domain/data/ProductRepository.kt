package com.anesabml.zalandoar.domain.data

import com.anesabml.zalandoar.domain.model.Product
import com.anesabml.zalandoar.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(): Flow<List<Product>>

    suspend fun getProductsByCategory(category: ProductCategory): Flow<List<Product>>

    suspend fun getProductsSortedByTime(): Flow<List<Product>>

    suspend fun getProduct(productId: Int): Flow<Product>

    suspend fun updateProduct(newProduct: Product)
}