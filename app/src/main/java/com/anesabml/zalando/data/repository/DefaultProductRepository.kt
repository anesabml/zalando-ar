package com.anesabml.zalando.data.repository

import com.anesabml.zalando.data.entity.ProductEntity
import com.anesabml.zalando.data.mapper.DatabaseMapper
import com.anesabml.zalando.domain.data.DataSource
import com.anesabml.zalando.domain.data.ProductRepository
import com.anesabml.zalando.domain.mapper.ModuleEntityMapper
import com.anesabml.zalando.domain.model.Product
import com.anesabml.zalando.domain.model.ProductCategory
import com.anesabml.zalando.utils.DefaultDispatcherProvider
import com.anesabml.zalando.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultProductRepository(
    private val dataSource: DataSource,
    private val mapper: ModuleEntityMapper<Product, ProductEntity> = DatabaseMapper,
    private val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
) : ProductRepository {

    override suspend fun getProducts(): Flow<List<Product>> =
        dataSource.getProducts()
            .map { list -> list.map(mapper::fromEntity) }

    override suspend fun getProductsByCategory(category: ProductCategory): Flow<List<Product>> =
        dataSource.getProductsByCategory(category)
            .map { list -> list.map(mapper::fromEntity) }

    override suspend fun getProductsSortedByTime(): Flow<List<Product>> =
        dataSource.getProductsSortedByTime()
            .map { list -> list.map(mapper::fromEntity) }

    override suspend fun getProduct(productId: Int): Flow<Product> =
        dataSource.getProduct(productId).map(mapper::fromEntity)

    override suspend fun updateProduct(newProduct: Product) =
        dataSource.updateProduct(mapper.toEntity(newProduct))
}