package com.anesabml.zalando.ui.productList

import android.view.View
import com.anesabml.zalando.domain.model.Product

interface ProductInteractionsListener {

    fun onClick(itemView: View, product: Product)

    fun onFavoriteClicked(product: Product)
}