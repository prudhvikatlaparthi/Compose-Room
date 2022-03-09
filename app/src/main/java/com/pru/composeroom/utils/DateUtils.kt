package com.pru.composeroom.utils

import android.app.DatePickerDialog
import android.content.Context
import com.pru.composeroom.R
import com.pru.composeroom.utils.Global.displayToast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    const val SERVER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val SIMPLE_DATE_FORMAT = "yyyyMMdd"
    const val DISPLAY_DATE_FORMAT = "d MMM yyyy"
    const val PREVIEW_DATE_FORMAT = "dd MMM yyyy | hh:mm a"
    const val DATE_TIME_FORMAT = "yyyyMMdd_HHmmss"

    data class MyDateValue(val dateString: String? = null, val date: Date? = null)

    fun Context.showDatePickerDialog(
        calendar: Calendar,
        formatter: SimpleDateFormat,
        disablePasteDates: Long = 0L,
        disableFutureDates: Long = 0L,
        listener: (MyDateValue) -> Unit
    ) {
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(
            this, R.style.DatePickerTheme,
            { _, _year, monthOfYear, dayOfMonth ->
                setWrapperData(
                    _year, monthOfYear, dayOfMonth,
                    listener, formatter
                )
            }, year, month, day
        )
//        if (disablePastDate) {
            if (disablePasteDates != 0L) {
                datePickerDialog.datePicker.minDate = disablePasteDates
            }/* else {
                datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
            }*/
//        }
        if (disableFutureDates != 0L) {
            datePickerDialog.datePicker.maxDate = disableFutureDates
        }
        datePickerDialog.show()
    }

    private fun Context.setWrapperData(
        year: Int,
        monthOfYear: Int,
        dayOfMonth: Int,
        listener: (MyDateValue) -> Unit,
        formatter: SimpleDateFormat
    ) {
        val month = monthOfYear + 1
        var dateFormat = "%d"
        dateFormat += if (month > 9) "%d" else "0%d"
        dateFormat += if (dayOfMonth > 9) "%d" else "0%d"
        val dateString = String.format(
            Locale.getDefault(), dateFormat, year, month,
            dayOfMonth
        )

        setDateText(
            listener,
            dateString,
            SIMPLE_DATE_FORMAT,
            formatter
        )
    }

    private fun Context.setDateText(
        listener: (MyDateValue) -> Unit,
        dateString: String,
        originalDataFormatString: String,
        formatter: SimpleDateFormat
    ) {
        val originalFormatter = SimpleDateFormat(
            originalDataFormatString,
            Locale.getDefault()
        )
        try {
            val date = originalFormatter.parse(dateString)
            date?.let {
                listener.invoke(MyDateValue(dateString = formatter.format(date), date = it))
                val calendar = Calendar.getInstance(Locale.US)
                calendar.time = date
                calendar.add(Calendar.HOUR, 1)
            } ?: displayToast(
                getString(R.string.error_select_data)
            )

        } catch (e: ParseException) {
            displayToast(
                getString(R.string.error_select_data)
            )
        }
    }
}