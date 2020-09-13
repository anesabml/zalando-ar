package com.anesabml.zalandoar.ui.productList

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.anesabml.zalandoar.databinding.ItemImageSliderBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class ProductImageSliderAdapter(
    private val images: List<String>
) : SliderViewAdapter<ProductImageSliderAdapter.ViewHolder>() {

    override fun getCount(): Int =
        images.size

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemImageSliderBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(images[position])

    class ViewHolder(private val binding: ItemImageSliderBinding) :
        SliderViewAdapter.ViewHolder(binding.root) {

        fun bind(imageUrl: String) {
            binding.image.load(imageUrl) {
                crossfade(true)
            }
        }
    }
}