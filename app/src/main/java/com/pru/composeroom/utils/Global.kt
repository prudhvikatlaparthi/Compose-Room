package com.pru.composeroom.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.pru.composeroom.MainActivity
import java.io.ByteArrayOutputStream

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

    fun extractByteArray(bitmap: Bitmap?): ByteArray? {
        return bitmap?.let {
            val stream = ByteArrayOutputStream()
            it.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.toByteArray()
        }
    }

    fun extractBitmap(byteArray: ByteArray?): Bitmap? {
        return byteArray?.let {
            BitmapFactory.decodeByteArray(it, 0, it.size)
        }
    }
}