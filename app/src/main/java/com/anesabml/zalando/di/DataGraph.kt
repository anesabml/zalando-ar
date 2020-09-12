package com.anesabml.zalando.di

import android.content.Context
import com.anesabml.zalando.data.datasource.LocalDataSource
import com.anesabml.zalando.data.db.AppDatabase
import com.anesabml.zalando.data.mapper.DatabaseMapper
import com.anesabml.zalando.data.repository.DefaultProductRepository
import com.anesabml.zalando.domain.data.DataSource

interface DataGraph {
    val dataSource: DataSource
    val productRepository: DefaultProductRepository
}

class BaseDataGraph(context: Context) : DataGraph {
    override val dataSource: DataSource by lazy {
        LocalDataSource(AppDatabase.getInstance(context).productDao())
    }

    override val productRepository: DefaultProductRepository by lazy {
        DefaultProductRepository(dataSource, DatabaseMapper)
    }
}