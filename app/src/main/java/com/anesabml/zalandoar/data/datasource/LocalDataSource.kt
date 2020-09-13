package com.anesabml.zalandoar.data.datasource

import com.anesabml.zalandoar.data.db.ProductDao
import com.anesabml.zalandoar.data.entity.ProductEntity
import com.anesabml.zalandoar.domain.data.DataSource
import com.anesabml.zalandoar.domain.model.ProductCategory
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

    override fun getProduct(productId: Int): Flow<ProductEntity> =
        productDao.getProduct(productId)

    override suspend fun updateProduct(productEntity: ProductEntity) =
        productDao.updateProduct(productEntity)
}