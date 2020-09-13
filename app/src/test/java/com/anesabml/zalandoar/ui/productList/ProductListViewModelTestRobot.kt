package com.anesabml.zalandoar.ui.productList

import androidx.lifecycle.SavedStateHandle
import com.anesabml.zalandoar.data.FakeProductsRepository
import com.anesabml.zalandoar.domain.model.Product
import com.anesabml.zalandoar.domain.model.ProductCategory
import com.anesabml.zalandoar.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ProductListViewModelTestRobot {

    private lateinit var viewModel: ProductListViewModel
    private val repository: FakeProductsRepository = FakeProductsRepository()

    fun buildViewModel() = apply {
        viewModel = ProductListViewModel(
            SavedStateHandle(mapOf(ProductListFragment.CATEGORY_ARG to ProductCategory.MEN)),
            repository
        )
    }

    fun emit(newFlow: Flow<List<Product>>) = apply {
        repository.emitFlow(newFlow)
    }

    fun getProducts() = apply {
        viewModel.getProducts()
    }

    fun updateProduct(product: Product) = apply {
        viewModel.updateProduct(product)
    }

    fun assertViewState(expectedViewState: ProductListViewState) = apply {
        val actualViewState = viewModel.state.getOrAwaitValue()
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }
}