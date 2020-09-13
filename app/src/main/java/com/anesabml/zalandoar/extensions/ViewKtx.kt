package com.anesabml.zalandoar.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(text: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, length).show()
}

fun View.showSnackBar(text: Int, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, length).show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}