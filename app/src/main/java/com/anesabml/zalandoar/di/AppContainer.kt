package com.anesabml.zalandoar.di

import android.app.Application
import com.anesabml.zalandoar.data.datasource.LocalDataSource
import com.anesabml.zalandoar.data.db.AppDatabase

class AppContainer(application: Application) {

    private val database = AppDatabase.getInstance(application)
    private val localDataSource =
        LocalDataSource(database.productDao())
}