package com.singularitycoder.testcomposestuff.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.singularitycoder.testcomposestuff.R

val PlayFairFamily = FontFamily(
    Font(resId = R.font.playfair_display_regular, weight = FontWeight.Normal),
    Font(resId = R.font.playfair_display_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(resId = R.font.playfair_display_bold, weight = FontWeight.Bold),
    Font(resId = R.font.playfair_display_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.playfair_display_black, weight = FontWeight.Black),
    Font(resId = R.font.playfair_display_black, weight = FontWeight.Black, style = FontStyle.Italic)
)