package com.anesabml.zalando.di

import android.app.Application
import com.anesabml.zalando.data.datasource.LocalDataSource
import com.anesabml.zalando.data.db.AppDatabase

class AppContainer(application: Application) {

    private val database = AppDatabase.getInstance(application)
    private val localDataSource =
        LocalDataSource(database.productDao())
}