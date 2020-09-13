package com.anesabml.zalandoar.utils

sealed class DataResponse<out T> {
    data class Success<out T>(val data: T) : DataResponse<T>()
    data class Error(val message: String) : DataResponse<Nothing>()
}