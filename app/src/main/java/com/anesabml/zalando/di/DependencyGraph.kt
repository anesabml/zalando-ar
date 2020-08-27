package com.anesabml.zalando.di

import android.content.Context

interface DependencyGraph {
    val dataGraph: DataGraph
    val viewModelFactoryGraph: ViewModelFactoryGraph
}

class BaseDependencyGraph(context: Context) : DependencyGraph {
    override val dataGraph: DataGraph = BaseDataGraph(context)
    override val viewModelFactoryGraph: ViewModelFactoryGraph =
        BaseViewModelFactoryGraph(dataGraph)
}