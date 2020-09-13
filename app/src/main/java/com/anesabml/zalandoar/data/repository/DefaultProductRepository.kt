package com.anesabml.zalandoar.data.repository

import com.anesabml.zalandoar.data.entity.ProductEntity
import com.anesabml.zalandoar.data.mapper.DatabaseMapper
import com.anesabml.zalandoar.domain.data.DataSource
import com.anesabml.zalandoar.domain.data.ProductRepository
import com.anesabml.zalandoar.domain.mapper.ModuleEntityMapper
import com.anesabml.zalandoar.domain.model.Product
import com.anesabml.zalandoar.domain.model.ProductCategory
import com.anesabml.zalandoar.utils.DefaultDispatcherProvider
import com.anesabml.zalandoar.utils.DispatcherProvider
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