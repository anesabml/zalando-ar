package com.anesabml.zalando.domain.data

import com.anesabml.zalando.data.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getProducts(): Flow<List<ProductEntity>>
    suspend fun insertAll(products: List<ProductEntity>)
}