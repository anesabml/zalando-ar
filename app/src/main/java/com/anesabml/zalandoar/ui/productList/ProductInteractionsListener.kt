package com.anesabml.zalandoar.ui.productList

import android.view.View
import com.anesabml.zalandoar.domain.model.Product

interface ProductInteractionsListener {

    fun onClick(itemView: View, product: Product)

    fun onFavoriteClicked(product: Product)
}