package com.anesabml.zalando.data.repository

import com.anesabml.zalando.data.entity.ProductEntity
import com.anesabml.zalando.data.mapper.DatabaseMapper
import com.anesabml.zalando.domain.data.DataSource
import com.anesabml.zalando.domain.data.IProductRepository
import com.anesabml.zalando.domain.mapper.ModuleEntityMapper
import com.anesabml.zalando.domain.model.Product
import com.anesabml.zalando.utils.DefaultDispatcherProvider
import com.anesabml.zalando.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepository(
    private val dataSource: DataSource,
    private val mapper: ModuleEntityMapper<Product, ProductEntity> = DatabaseMapper,
    private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
) : IProductRepository {
    override suspend fun getProducts(): Flow<List<Product>> {
        return dataSource.getProducts()
            .map { list -> list.map { entity -> mapper.fromEntity(entity) } }
    }
}