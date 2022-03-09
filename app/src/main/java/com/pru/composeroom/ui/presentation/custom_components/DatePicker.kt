package com.pru.composeroom.ui.presentation.custom_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pru.composeroom.utils.DateUtils
import com.pru.composeroom.utils.DateUtils.showDatePickerDialog
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DatePicker(
    dateLabel: String,
    myDateValue: DateUtils.MyDateValue,
    disablePasteDates: Long = 0L,
    disableFutureDates: Long = 0L,
    onDateChangeListener: (myDateValue: DateUtils.MyDateValue) -> Unit
) {
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = dateLabel,
            fontSize = 16.sp,
            color = Color.White
        )
        Row(modifier = Modifier.clickable {
            val cal = Calendar.getInstance()
            if (myDateValue.date != null) {
                cal.time = myDateValue.date
            }
            context.showDatePickerDialog(
                calendar = cal,
                formatter = SimpleDateFormat(DateUtils.DISPLAY_DATE_FORMAT, Locale.getDefault()),
                disableFutureDates = disableFutureDates,
                disablePasteDates = disablePasteDates,
            ) { myDateValue ->
                onDateChangeListener.invoke(myDateValue)
            }
        }) {
            Text(
                text = myDateValue.dateString ?: "",
                color = Color.White,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(Icons.Filled.Event, contentDescription = null, tint = Color.White)
        }
    }
}