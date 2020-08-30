package com.anesabml.zalando.ui.productList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.anesabml.zalando.R
import com.anesabml.zalando.databinding.FragmentProductListBinding
import com.anesabml.zalando.domain.model.ProductCategory
import com.anesabml.zalando.extensions.viewBinding
import com.anesabml.zalando.viewModelFactoryGraph

/**
 * A simple [Fragment] subclass to show a list of products
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private val binding: FragmentProductListBinding by viewBinding(FragmentProductListBinding::bind)
    private val viewModel: ProductListViewModel by viewModels {
        requireContext().viewModelFactoryGraph().getProductListViewModelFactory(this)
    }
    private lateinit var _adapter: ProductListRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.state.observe(
            viewLifecycleOwner,
            {
                updateState(it)
            }
        )
    }

    private fun updateState(viewState: ProductListViewState) {
        when (viewState) {
            ProductListViewState.Loading -> binding.viewFlipper.displayedChild = LOADING_VIEW_INDEX
            is ProductListViewState.Error -> binding.viewFlipper.displayedChild = ERROR_VIEW_INDEX
            is ProductListViewState.Success -> {
                binding.viewFlipper.displayedChild = RECYCLER_VIEW_INDEX
                _adapter.submitList(viewState.data)
            }
        }
    }

    private fun setupRecyclerView() {
        _adapter = ProductListRecyclerViewAdapter(
            productOnClickListener = {
                Toast.makeText(requireContext(), "Fuck", Toast.LENGTH_SHORT).show()
            }
        )

        with(binding.recyclerView) {
            layoutManager =
                StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
            adapter = _adapter
            addItemDecoration(GridSpacingItemDecoration(16F))
        }
    }

    companion object {
        private const val SPAN_COUNT = 2
        private const val RECYCLER_VIEW_INDEX = 0
        private const val ERROR_VIEW_INDEX = 1
        private const val LOADING_VIEW_INDEX = 2
        const val CATEGORY_ARG = "category"

        fun newInstance(category: ProductCategory) =
            ProductListFragment().apply {
                arguments = bundleOf(CATEGORY_ARG to category)
            }
    }
}