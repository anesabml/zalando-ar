package com.anesabml.zalando.data.datasource

import com.anesabml.zalando.data.db.ProductDao
import com.anesabml.zalando.data.entity.ProductEntity
import com.anesabml.zalando.domain.data.DataSource
import com.anesabml.zalando.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val productDao: ProductDao) : DataSource {

    override suspend fun insertAll(products: List<ProductEntity>) =
        productDao.insertAll(products)

    override fun getProducts(): Flow<List<ProductEntity>> =
        productDao.getAll()

    override fun getProductsByCategory(category: ProductCategory): Flow<List<ProductEntity>> =
        productDao.getProductsByCategory(category)

    override fun getProductsSortedByTime(): Flow<List<ProductEntity>> =
        productDao.getProductsSortedByTime()
}