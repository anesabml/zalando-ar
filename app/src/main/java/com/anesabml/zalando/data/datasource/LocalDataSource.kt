package com.anesabml.zalando.data.datasource

import com.anesabml.zalando.data.db.ProductDao
import com.anesabml.zalando.data.entity.ProductEntity
import com.anesabml.zalando.domain.data.DataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val productDao: ProductDao) : DataSource {

    override fun getProducts(): Flow<List<ProductEntity>> =
        productDao.getAll()

    override suspend fun insertAll(products: List<ProductEntity>) =
        productDao.insertAll(products)
}