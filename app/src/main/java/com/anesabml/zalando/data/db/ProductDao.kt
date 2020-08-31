package com.anesabml.zalando.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anesabml.zalando.data.entity.ProductEntity
import com.anesabml.zalando.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM products")
    fun getAll(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE category = :category")
    fun getProductsByCategory(category: ProductCategory): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products ORDER BY addedAt")
    fun getProductsSortedByTime(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProduct(productId: Int): Flow<ProductEntity>
}