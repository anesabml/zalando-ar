package com.anesabml.zalando.ui.productDetails

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.anesabml.zalando.R
import com.anesabml.zalando.databinding.FragmentProductDetailsBinding
import com.anesabml.zalando.domain.model.Product
import com.anesabml.zalando.extensions.hide
import com.anesabml.zalando.extensions.show
import com.anesabml.zalando.extensions.showSnackBar
import com.anesabml.zalando.extensions.viewBinding
import com.anesabml.zalando.ui.productList.ProductImageSliderAdapter
import com.anesabml.zalando.viewModelFactoryGraph

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {

    private val binding: FragmentProductDetailsBinding by viewBinding(FragmentProductDetailsBinding::bind)
    private val viewModel: ProductDetailsViewModel by viewModels {
        requireContext().viewModelFactoryGraph().getProductDetailsViewModelFactory(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.state.observe(viewLifecycleOwner) {
            updateState(it)
        }
    }

    private fun updateState(viewState: ProductDetailsViewState) {
        when (viewState) {
            ProductDetailsViewState.Loading -> showProgressBar()
            is ProductDetailsViewState.Success -> showProductDetails(viewState.data)
            is ProductDetailsViewState.Error -> showError(viewState.message)
        }
    }

    private fun showProgressBar() {
        binding.progressBar.show()
        binding.groupProductDetails.hide()
    }

    private fun showProductDetails(product: Product) {
        with(binding) {
            progressBar.hide()
            groupProductDetails.show()
            val favoriteImageViewRes =
                if (product.isBookmarked) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            favoriteIcon.setImageResource(favoriteImageViewRes)
            favoriteIcon.setOnClickListener {
                val updatedProduct = product.copy(isBookmarked = !product.isBookmarked)
                viewModel.updateProduct(updatedProduct)
            }
            arIcon.setOnClickListener {
                findNavController().navigate(R.id.action_productDetailsFragment_to_productArFragment)
            }
            productImageSlider.setSliderAdapter(ProductImageSliderAdapter(product.images))
            productName.text = product.name
            productPrice.text = getString(R.string.currency, product.price)
            productDescription.text = product.description
            buttonAddToCard.setOnClickListener {
                viewModel.addProductToCard()
            }
            color1.color = Color.parseColor(product.availableColors[0])
            color2.color = Color.parseColor(product.availableColors[1])
            color3.color = Color.parseColor(product.availableColors[2])
            color4.color = Color.parseColor(product.availableColors[3])
            buttonBuyNow.setOnClickListener {}
        }
    }

    private fun showError(msg: String) {
        binding.progressBar.hide()
        binding.groupProductDetails.hide()
        binding.root.showSnackBar(R.string.error_try_again)
    }

    companion object {

        const val PRODUCT_ID_ARG = "product_id"

        fun newInstance(productId: Int): ProductDetailsFragment =
            ProductDetailsFragment().apply {
                arguments = bundleOf(PRODUCT_ID_ARG to productId)
            }
    }
}