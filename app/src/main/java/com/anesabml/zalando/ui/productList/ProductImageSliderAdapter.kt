package com.anesabml.zalando.ui.productList

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.anesabml.zalando.databinding.ItemImageSliderBinding
import com.anesabml.zalando.domain.model.Product
import com.smarteist.autoimageslider.SliderViewAdapter

class ProductImageSliderAdapter(
    private val product: Product
) : SliderViewAdapter<ProductImageSliderAdapter.ViewHolder>() {

    override fun getCount(): Int =
        product.images.size

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemImageSliderBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(product.images[position])

    class ViewHolder(private val binding: ItemImageSliderBinding) :
        SliderViewAdapter.ViewHolder(binding.root) {

        fun bind(imageUrl: String) {
            binding.image.load(imageUrl) {
                crossfade(true)
            }
        }
    }
}