package com.pru.composeroom.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.pru.composeroom.MainActivity

object Global {
    @Composable
    fun GetMainActivity() = (LocalContext.current as? MainActivity)

    @Composable
    fun DisplayToast(message: String?) {
        val context = LocalContext.current
        context.displayToast(message = message)
    }

    fun Context.displayToast(message: String?) {
        message?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}