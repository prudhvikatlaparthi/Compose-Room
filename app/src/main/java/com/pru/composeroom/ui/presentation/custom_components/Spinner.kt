package com.pru.composeroom.ui.presentation.custom_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T> Spinner(
    modifier: Modifier,
    spinnerLabel: String,
    spinnerValue: String,
    showSpinner: Boolean,
    spinnerData: List<T>,
    onOpenCloseSpinner: (isShow: Boolean) -> Unit,
    onItemChangeListener: (item: T) -> Unit,
    itemContent: @Composable RowScope.(item: T) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = spinnerLabel,
            fontSize = 16.sp,
            color = Color.White
        )
        Row(modifier = Modifier.clickable {
            onOpenCloseSpinner.invoke(true)
        }) {
            Text(
                text = spinnerValue,
                color = Color.White,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(Icons.Filled.ArrowDropDown, contentDescription = null, tint = Color.White)
            DropdownMenu(expanded = showSpinner, onDismissRequest = {
                onOpenCloseSpinner.invoke(false)
            }) {
                spinnerData.forEach { item ->
                    DropdownMenuItem(onClick = {
                        onOpenCloseSpinner.invoke(false)
                        onItemChangeListener.invoke(item)
                    }) {
                        itemContent(item)
                    }
                }
            }
        }
    }
}