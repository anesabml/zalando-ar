package com.anesabml.zalando.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.anesabml.zalando.R
import com.anesabml.zalando.databinding.FragmentProductDetailsBinding
import com.anesabml.zalando.domain.model.Product
import com.anesabml.zalando.extensions.hide
import com.anesabml.zalando.extensions.show
import com.anesabml.zalando.extensions.showSnackBar
import com.anesabml.zalando.extensions.viewBinding
import com.anesabml.zalando.ui.productDetails.ProductDetailsViewModel
import com.anesabml.zalando.ui.productDetails.ProductDetailsViewState
import com.anesabml.zalando.ui.productList.ProductImageSliderAdapter
import com.anesabml.zalando.viewModelFactoryGraph

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {

    private val binding: FragmentProductDetailsBinding by viewBinding(FragmentProductDetailsBinding::bind)
    private val viewModel: ProductDetailsViewModel by viewModels {
        requireContext().viewModelFactoryGraph().getProductDetailsViewModelFactory(this)
    }
    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*sharedElementEnterTransition =
            MaterialContainerTransform().apply {
                duration = resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
            }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.productImageSlider.transitionName = args.productId.toString()

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
            productImageSlider.setSliderAdapter(ProductImageSliderAdapter(product, null))
            productName.text = product.name
            productPrice.text = getString(R.string.currency, product.price)
            productDescription.text = product.description
            buttonAddToCard.setOnClickListener {
                viewModel.addProductToCard()
            }
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