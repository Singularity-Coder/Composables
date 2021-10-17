package com.singularitycoder.testcomposestuff.ui.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.singularitycoder.testcomposestuff.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

val hobbyList = listOf("Meditation", "Yoga", "Computer Programming", "Hacking", "3D Animation", "Story Telling", "Graphic Design", "Inventing")
val menuList = listOf("Preview", "Share", "Get Link")
val bombList = listOf("RDS-220", "B41", "TX-21")
val menuIconList = listOf(R.drawable.ic_baseline_visibility_24, R.drawable.ic_baseline_person_add_24, R.drawable.ic_baseline_link_24)

// https://stackoverflow.com/questions/68909340/how-to-show-snackbar-with-a-button-onclick-in-jetpack-compose
fun showFeedback(
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    message: String = "NA",
    btnText: String = "NA",
    duration: SnackbarDuration = SnackbarDuration.Short,
    btnAction: () -> Unit = {},
    dismissAction: () -> Unit = {}
) {
    // Queues snackbars. So if i click 100 times then I will see all 100 snacks over a period of time. This was handled well in XML Snackbar
    coroutineScope.launch {
        val snackbar = scaffoldState.snackbarHostState.showSnackbar(message = message, actionLabel = if ("NA" != btnText) btnText else null, duration = duration)
        when (snackbar) {
            SnackbarResult.ActionPerformed -> btnAction.invoke()
            SnackbarResult.Dismissed -> dismissAction.invoke()
        }
    }
}

// https://stackoverflow.com/questions/64675386/how-to-get-activity-in-compose
fun Context.getActivity(): AppCompatActivity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is AppCompatActivity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    return null
}

fun String?.isNullOrBlankOrNaOrNullString(): Boolean {
    return this.isNullOrBlank() || "null" == this.toLowCase().trim() || "na" == this.toLowCase().trim()
}

fun String.toLowCase(): String = this.lowercase(Locale.ROOT)

fun String.toUpCase(): String = this.uppercase(Locale.ROOT)

fun String.capFirstChar(): String = this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

@Stable
fun myColor(color: String): Color = Color("0xFF$color".toLong())

class CustomShape : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val path = Path().apply {
            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}

enum class MyColors(val color: Color) {
    RED(Color.Red), GREEN(Color.Green), BLUE(Color.Blue)
}

enum class Composables(val value: String) {
    ON_BACKPRESS(value = "On Backpress"),
    ALERT_DIALOG(value = "Alert Dialog"),
    SNACKBAR(value = "Snackbar"),
    BUTTON(value = "Button"),
    TEXT(value = "Text"),
    TEXT_FIELDS(value = "Text Fields"),
    CARD(value = "Card"),
    SHAPES(value = "Shapes"),
    ICON_BUTTON(value = "Icon Button"),
    IMAGES(value = "Images"),
    GESTURES(value = "Gestures"),
    ANIMATIONS(value = "Animations"),
    MENU(value = "Menu"),
    PROGRESS_BARS(value = "Progress Bars"),
    SWITCH(value = "Switch"),
    CHECKBOX(value = "Checkbox"),
    RADIO_BUTTON(value = "Radio Button"),
    SLIDER(value = "Slider"),
    VERTICAL_LIST(value = "Vertical List"),
    HORIZONTAL_LIST(value = "Horizontal List"),
    VERTICAL_GRID_LIST(value = "Vertical Grid List"),
    FLOATING_ACTION_BUTTON(value = "Floating Action Button"),
    EXTENDED_FLOATING_ACTION_BUTTON(value = "Extended Floating Action Button"),
    CANVAS(value = "Canvas"),
    BOTTOM_NAVIGATION_BAR(value = "Bottom Navigation Bar"),
    BOTTOM_APP_BAR(value = "Bottom App Bar"),
    TOP_APP_BAR(value = "Top App Bar"),
    BACKDROP_SCAFFOLD(value = "Backdrop Scaffold"),
    TAB_ROW(value = "Tab Row"),
    TAB(value = "Tab"),
    SCROLLABLE_TAB_ROW(value = "Scrollable Tab Row"),
    DIVIDER(value = "Divider"),
    FLOW_ROW(value = "FlowRow"),
    FLOW_COLUMN(value = "FlowColumn"),
    SCROLLABLE_ROW(value = "Scrollable Row"),
    SCROLLABLE_COLUMN(value = "Scrollable Column"),
    SPACER(value = "Spacer"),
    SCAFFOLD_LAYOUT(value = "Scaffold Layout"),
    STACK_LAYOUT(value = "Stack Layout"),
    CONSTRAINT_LAYOUT(value = "Constraint Layout"),
    BOX_LAYOUT(value = "Box Layout"),
    BOX_WITH_CONSTRAINTS_LAYOUT(value = "BoxWithConstraints Layout"),
    BADGE_BOX_LAYOUT(value = "BadgeBox Layout"),
    ANDROID_VIEW(value = "Android Views"),
    MODAL_BOTTOM_SHEET(value = "Modal Bottom Sheet"),
    MODAL_DRAWER(value = "Modal Drawer"),
    SURFACE(value = "Surface"),
}