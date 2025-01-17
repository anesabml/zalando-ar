package com.anesabml.zalandoar.ui.productList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anesabml.zalandoar.R
import com.anesabml.zalandoar.data.FakeData
import com.anesabml.zalandoar.domain.model.Product
import com.anesabml.zalandoar.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductListViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testRobot = ProductListViewModelTestRobot()

    @Test
    fun `successful request returns list of products`() =
        coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
            val expectedList = FakeData.products
            val flow = flowOf(expectedList)

            testRobot
                .emit(flow)
                .buildViewModel()
//                .assertViewState(ProductListViewState.Loading)
                .assertViewState(ProductListViewState.Success(expectedList))
        }

    @Test
    fun `failure request should catch exception`() =
        coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
            val flow = flow<List<Product>> {
                throw Throwable("Error")
            }
            testRobot
                .emit(flow)
                .buildViewModel()
//                .assertViewState(ProductListViewState.Loading)
                .assertViewState(ProductListViewState.Error(R.string.error_getting_products))
        }

    @Test
    fun `update product request should succeed`() =
        coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
            val expectedList = FakeData.products.toMutableList()
            val flow = flowOf(expectedList)
            val updatedProduct = expectedList[0].copy(isBookmarked = true)
            expectedList[0] = updatedProduct

            testRobot
                .emit(flow)
                .buildViewModel()
//                .assertViewState(ProductListViewState.Loading)
                .assertViewState(ProductListViewState.Success(expectedList))
                .updateProduct(updatedProduct)
                .getProducts()
                .assertViewState(ProductListViewState.Success(expectedList))
        }
}