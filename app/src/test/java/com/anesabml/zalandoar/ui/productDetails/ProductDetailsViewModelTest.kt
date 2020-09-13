package com.anesabml.zalandoar.ui.productDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anesabml.zalandoar.data.FakeData
import com.anesabml.zalandoar.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductDetailsViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testRobot = ProductDetailsViewModelTestRobot()

    @Test
    fun `get product by id request should succeed`() =
        coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
            val expectedList = FakeData.products
            val flow = flowOf(expectedList)
            val expectedProduct = expectedList[0]

            testRobot
                .emit(flow)
                .buildViewModel()
//                .assertViewState(ProductListViewState.Loading)
                .assertViewState(ProductDetailsViewState.Success(expectedProduct))
        }

    @Test
    fun `update product request should succeed`() =
        coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
            val expectedList = FakeData.products
            val flow = flowOf(expectedList)
            val expectedProduct = expectedList[0]
            val updatedProduct = expectedList[0].copy(isBookmarked = true)

            testRobot
                .emit(flow)
                .buildViewModel()
//                .assertViewState(ProductListViewState.Loading)
                .assertViewState(ProductDetailsViewState.Success(expectedProduct))
                .updateProduct(updatedProduct)
                .getProduct()
                .assertViewState(ProductDetailsViewState.Success(updatedProduct))
        }
}