package com.pru.composeroom.resource

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(private val context: Context) {

    fun obtainString(@StringRes id: Int): String = context.getString(id)
}