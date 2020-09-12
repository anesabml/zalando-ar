package com.anesabml.zalando.ui.productDetails

import androidx.lifecycle.SavedStateHandle
import com.anesabml.zalando.data.FakeProductsRepository
import com.anesabml.zalando.domain.model.Product
import com.anesabml.zalando.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ProductDetailsViewModelTestRobot {

    private lateinit var viewModel: ProductDetailsViewModel
    private val repository: FakeProductsRepository = FakeProductsRepository()

    fun buildViewModel() = apply {
        viewModel = ProductDetailsViewModel(
            SavedStateHandle(mapOf(ProductDetailsFragment.PRODUCT_ID_ARG to 1)),
            repository
        )
    }

    fun emit(newFlow: Flow<List<Product>>) = apply {
        repository.emitFlow(newFlow)
    }

    fun getProduct() = apply {
        viewModel.getProduct()
    }

    fun updateProduct(product: Product) = apply {
        viewModel.updateProduct(product)
    }

    fun assertViewState(expectedViewState: ProductDetailsViewState) = apply {
        val actualViewState = viewModel.state.getOrAwaitValue()
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }
}