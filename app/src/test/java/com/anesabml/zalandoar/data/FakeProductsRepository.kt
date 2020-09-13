package com.anesabml.zalandoar.data

import com.anesabml.zalandoar.domain.data.ProductRepository
import com.anesabml.zalandoar.domain.model.Product
import com.anesabml.zalandoar.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class FakeProductsRepository : ProductRepository {

    private lateinit var flow: Flow<List<Product>>

    override suspend fun getProducts(): Flow<List<Product>> {
        return flow
    }

    override suspend fun getProductsByCategory(category: ProductCategory): Flow<List<Product>> {
        return flow
    }

    override suspend fun getProductsSortedByTime(): Flow<List<Product>> {
        return flow
    }

    override suspend fun getProduct(productId: Int): Flow<Product> {
        return flow.map { list -> list.find { product -> product.id == productId }!! }
    }

    override suspend fun updateProduct(newProduct: Product) {
        val list = flow.toList(mutableListOf())[0].toMutableList()
        val oldProduct = list.find { it.id == newProduct.id }!!
        list[list.indexOf(oldProduct)] = newProduct
        flow = flowOf(list)
    }

    fun emitFlow(newFlow: Flow<List<Product>>) {
        flow = newFlow
    }
}
