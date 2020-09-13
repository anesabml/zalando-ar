package com.anesabml.zalandoar.di

import android.content.Context
import com.anesabml.zalandoar.data.datasource.LocalDataSource
import com.anesabml.zalandoar.data.db.AppDatabase
import com.anesabml.zalandoar.data.mapper.DatabaseMapper
import com.anesabml.zalandoar.data.repository.DefaultProductRepository
import com.anesabml.zalandoar.domain.data.DataSource

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