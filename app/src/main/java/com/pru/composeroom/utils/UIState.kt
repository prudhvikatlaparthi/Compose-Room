package com.pru.composeroom.utils

sealed class UIState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : UIState<T>(data)
    class Error<T>(message: String, data: T? = null) : UIState<T>(data, message)
    class Loading<T> : UIState<T>()
    class None<T> : UIState<T>()
}