package com.anesabml.zalandoar

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import com.anesabml.zalandoar.di.BaseDependencyGraph
import com.anesabml.zalandoar.di.DataGraph
import com.anesabml.zalandoar.di.DependencyGraph
import com.anesabml.zalandoar.di.ViewModelFactoryGraph
import timber.log.Timber

class ZalandoApplication : Application(), Configuration.Provider {

    val dependencyGraph = BaseDependencyGraph(this)

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

fun Context.dependencyGraph(): DependencyGraph {
    return (this.applicationContext as ZalandoApplication).dependencyGraph
}

fun Context.dataGraph(): DataGraph {
    return this.dependencyGraph().dataGraph
}

fun Context.viewModelFactoryGraph(): ViewModelFactoryGraph {
    return this.dependencyGraph().viewModelFactoryGraph
}
