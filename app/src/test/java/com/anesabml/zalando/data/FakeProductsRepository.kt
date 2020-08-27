package com.anesabml.zalando.data

import com.anesabml.zalando.domain.data.IProductRepository
import com.anesabml.zalando.domain.model.Product
import kotlinx.coroutines.flow.Flow

class FakeProductsRepository : IProductRepository {

    private lateinit var flow: Flow<List<Product>>

    override suspend fun getProducts(): Flow<List<Product>> {
        return flow
    }

    fun emitFlow(newFlow: Flow<List<Product>>) {
        flow = newFlow
    }
}
