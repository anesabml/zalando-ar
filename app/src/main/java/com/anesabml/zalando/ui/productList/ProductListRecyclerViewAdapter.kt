package com.anesabml.zalando.ui.productList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anesabml.zalando.R
import com.anesabml.zalando.databinding.ItemProductBinding
import com.anesabml.zalando.domain.model.Product
import com.anesabml.zalando.utils.DiffItemCallback

class ProductListRecyclerViewAdapter(private val productOnClickListener: ProductOnClickListener) :
    ListAdapter<Product, ProductListRecyclerViewAdapter.ProductItemViewHolder>(DiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductItemViewHolder(ItemProductBinding.inflate(layoutInflater!!, parent, false))
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val currentProduct = getItem(position)
        holder.bind(currentProduct)
    }

    inner class ProductItemViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            val animation = AnimationUtils.loadAnimation(
                binding.root.context,
                R.anim.item_animation_from_bottom
            )
            val favoriteImageViewRes =
                if (product.isBookmarked) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            with(binding) {
                root.animation = animation
                productImageSlider.setSliderAdapter(ProductImageSliderAdapter(product, productOnClickListener::onClick))
                favoriteImage.setImageResource(favoriteImageViewRes)
            }
        }
    }
}