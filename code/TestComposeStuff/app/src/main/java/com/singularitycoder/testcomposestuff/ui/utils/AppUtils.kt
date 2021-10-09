package com.singularitycoder.testcomposestuff.ui.utils

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.singularitycoder.testcomposestuff.R
import java.util.*

val hobbyList = listOf("Meditation", "Yoga", "Comuter Programming", "Hacking", "3D Animation", "Story Telling", "Graphic Design", "Inventing")
val menuList = listOf("Preview", "Share", "Get Link")
val menuIconList = listOf(R.drawable.ic_baseline_visibility_24, R.drawable.ic_baseline_person_add_24, R.drawable.ic_baseline_link_24)
val bombList = listOf("RDS-220", "B41", "TX-21")

fun String?.isNullOrBlankOrNaOrNullString(): Boolean {
    return this.isNullOrBlank() || "null" == this.toLowCase().trim() || "na" == this.toLowCase().trim()
}

fun String.toLowCase(): String = this.lowercase(Locale.ROOT)

fun String.toUpCase(): String = this.uppercase(Locale.ROOT)

fun String.capFirstChar(): String = this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

@Stable
fun MyColor(color: String): Color = Color("0xFF$color".toLong())