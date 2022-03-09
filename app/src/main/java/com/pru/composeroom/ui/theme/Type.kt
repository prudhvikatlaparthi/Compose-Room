package com.pru.composeroom.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.pru.composeroom.R

val fonts = FontFamily(
    Font(R.font.opensans_extra_bold, FontWeight.ExtraBold),
    Font(R.font.opensans_light, FontWeight.Light),
    Font(R.font.opensans_medium, FontWeight.Medium),
    Font(R.font.opensans_regular, FontWeight.Normal),
    Font(R.font.opensans_bold, FontWeight.Bold),
    Font(R.font.opensans_semibold, FontWeight.SemiBold),
)
val Typography = Typography(
    defaultFontFamily = fonts
)