package com.anesabml.zalando.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.anesabml.zalando.CoroutineTestRule
import com.anesabml.zalando.DummyData
import com.anesabml.zalando.data.db.AppDatabase
import com.anesabml.zalando.data.db.ProductDao
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ProductDaoTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var productDao: ProductDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() = coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        productDao = database.productDao()
        productDao.insertAll(DummyData.products)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetList() = coroutineTestRule.testCoroutineDispatcher.runBlockingTest {
        val actualList = productDao.getAll().take(1).toList()[0]
        assertThat(actualList).isEqualTo(DummyData.products)
    }
}