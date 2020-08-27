package com.anesabml.zalando.domain.data

import com.anesabml.zalando.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    suspend fun getProducts(): Flow<List<Product>>
}