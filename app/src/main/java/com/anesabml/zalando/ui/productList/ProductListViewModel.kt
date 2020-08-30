package com.anesabml.zalando.ui.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anesabml.zalando.domain.data.IProductRepository
import com.anesabml.zalando.domain.model.Product
import com.anesabml.zalando.domain.model.ProductCategory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ProductListViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: IProductRepository
) : ViewModel() {

    private val category = savedStateHandle.get<ProductCategory>(ProductListFragment.CATEGORY_ARG)!!

    private var _state: MutableLiveData<ProductListViewState> =
        MutableLiveData(ProductListViewState.Loading)
    val state: LiveData<ProductListViewState>
        get() = _state

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            val result = when (category) {
                ProductCategory.NEW -> getProductsSortedByTime()
                else -> getProductsByCategory(category)
            }
            result
                .onStart {
                    _state.value = ProductListViewState.Loading
                }
                .catch {
                    _state.value =
                        ProductListViewState.Error(it.message ?: "Error trying to get products")
                }
                .collect {
                    _state.value = ProductListViewState.Success(it)
                }
        }
    }

    private suspend fun getProductsSortedByTime(): Flow<List<Product>> =
        repository.getProductsSortedByTime()

    private suspend fun getProductsByCategory(category: ProductCategory): Flow<List<Product>> =
        repository.getProductsByCategory(this.category)
}