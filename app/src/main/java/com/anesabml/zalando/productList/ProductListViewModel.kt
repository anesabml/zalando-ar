package com.anesabml.zalando.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anesabml.zalando.domain.data.IProductRepository
import com.anesabml.zalando.productList.state.ProductListViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ProductListViewModel(
    private val repository: IProductRepository
) : ViewModel() {

    private var _state: MutableLiveData<ProductListViewState> =
        MutableLiveData(ProductListViewState.Loading)
    val state: LiveData<ProductListViewState>
        get() = _state

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            repository.getProducts()
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
}