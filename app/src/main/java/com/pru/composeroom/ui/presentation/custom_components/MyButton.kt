package com.pru.composeroom.ui.presentation.custom_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pru.composeroom.ui.theme.MainColor

@Composable
fun MyButton(
    modifier: Modifier,
    buttonTitle: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = MainColor
        ), shape = RoundedCornerShape(10.dp), modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = buttonTitle,
            modifier = Modifier.padding(4.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}