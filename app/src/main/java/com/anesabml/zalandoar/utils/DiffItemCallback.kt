package com.anesabml.zalandoar.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DiffItemCallback<T : Differentiable> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.diffId == newItem.diffId

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.areContentsTheSame(newItem)
}
