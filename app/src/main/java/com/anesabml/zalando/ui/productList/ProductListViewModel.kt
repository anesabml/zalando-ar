package com.anesabml.zalando.ui.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anesabml.zalando.R
import com.anesabml.zalando.domain.data.ProductRepository
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
    private val repository: ProductRepository
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
                else -> getProductsByCategory()
            }
            result
                .onStart {
                    _state.value = ProductListViewState.Loading
                }
                .catch {
                    _state.value =
                        ProductListViewState.Error(R.string.error_getting_products)
                }
                .collect {
                    if (it.isEmpty()) {
                        _state.value =
                            ProductListViewState.Error(R.string.no_products)
                    } else {
                        _state.value = ProductListViewState.Success(it)
                    }
                }
        }
    }

    private suspend fun getProductsSortedByTime(): Flow<List<Product>> =
        repository.getProductsSortedByTime()

    private suspend fun getProductsByCategory(): Flow<List<Product>> =
        repository.getProductsByCategory(this.category)

    fun updateProduct(updatedProduct: Product) {
        viewModelScope.launch {
            repository.updateProduct(updatedProduct)
        }
    }
}