package com.anesabml.zalando.ui.productList

import androidx.lifecycle.SavedStateHandle
import com.anesabml.zalando.data.FakeProductsRepository
import com.anesabml.zalando.domain.model.Product
import com.anesabml.zalando.domain.model.ProductCategory
import com.anesabml.zalando.utils.getOrAwaitValue
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

    fun assertViewState(expectedViewState: ProductListViewState) = apply {
        val actualViewState = viewModel.state.getOrAwaitValue()
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }
}