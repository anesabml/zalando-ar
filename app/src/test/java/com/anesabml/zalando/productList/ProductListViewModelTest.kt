package com.anesabml.zalando.productList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anesabml.zalando.data.DummyData
import com.anesabml.zalando.domain.model.Product
import com.anesabml.zalando.productList.state.ProductListViewState
import com.anesabml.zalando.utils.CoroutineTestRule
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
            val expectedList = DummyData.products
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
                .assertViewState(ProductListViewState.Error("Error"))
        }
}