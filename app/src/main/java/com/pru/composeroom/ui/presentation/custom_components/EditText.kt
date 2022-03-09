package com.pru.composeroom.ui.presentation.custom_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    onClear: () -> Unit,
    isPassword: Boolean = false,
    isPasswordToggle: Boolean = false,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    passwordToggle: ((Boolean) -> Unit)? = null,
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint, fontSize = 16.sp)
        },
        maxLines = maxLines,
        singleLine = singleLine,
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        textStyle = TextStyle(fontSize = 16.sp),
        visualTransformation = if (isPasswordToggle) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier.fillMaxWidth(),
        trailingIcon = {
            Row(modifier = Modifier.padding(vertical = 10.dp)) {
                if (value.isNotEmpty()) {
                    Icon(
                        Icons.Filled.Cancel,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            onClear.invoke()
                        })
                }
                if (isPassword) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        if (isPasswordToggle) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            passwordToggle?.invoke(!isPasswordToggle)
                        })
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        },
        keyboardActions = keyboardActions ?: KeyboardActions(),
        keyboardOptions = keyboardOptions ?: KeyboardOptions()
    )
}