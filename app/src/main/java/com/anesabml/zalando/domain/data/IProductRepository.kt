package com.anesabml.zalando.domain.data

import com.anesabml.zalando.domain.model.Product
import com.anesabml.zalando.utils.DataResponse
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    suspend fun getProducts(): Flow<DataResponse<List<Product>>>
}