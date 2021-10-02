package com.singularitycoder.testcomposestuff.ui.utils

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import java.util.*

fun String?.isNullOrBlankOrNaOrNullString(): Boolean {
    return this.isNullOrBlank() || "null" == this.toLowCase().trim() || "na" == this.toLowCase().trim()
}

fun String.toLowCase(): String = this.lowercase(Locale.ROOT)

fun String.toUpCase(): String = this.uppercase(Locale.ROOT)

fun String.capFirstChar(): String = this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

@Stable
fun MyColor(color: String): Color = Color("0xFF$color".toLong())