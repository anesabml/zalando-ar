package com.anesabml.zalando.utils

interface Differentiable {

    val diffId: Int

    fun areContentsTheSame(newItem: Differentiable): Boolean =
        this == newItem
}
