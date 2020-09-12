package com.anesabml.zalando.ui.productDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anesabml.zalando.domain.data.ProductRepository
import com.anesabml.zalando.domain.model.Product
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ProductDetailsViewModel(
    handle: SavedStateHandle,
    private val repository: ProductRepository
) : ViewModel() {

    private val productId = handle.get<Int>(ProductDetailsFragment.PRODUCT_ID_ARG) ?: -1

    private val _state: MutableLiveData<ProductDetailsViewState> =
        MutableLiveData(ProductDetailsViewState.Loading)
    val state: LiveData<ProductDetailsViewState> = _state

    init {
        getProduct()
    }

    private fun getProduct() {
        viewModelScope.launch {
            repository.getProduct(productId)
                .catch {
                    _state.value =
                        ProductDetailsViewState.Error(it.message ?: "Error trying to get product")
                }
                .collect {
                    _state.value = ProductDetailsViewState.Success(it)
                }
        }
    }

    fun addProductToCard() {
        // TODO
    }

    fun updateProduct(updatedProduct: Product) {
        viewModelScope.launch {
            repository.updateProduct(updatedProduct)
        }
    }
}
