package com.singularitycoder.testcomposestuff.ui.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.annotation.DrawableRes
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
import kotlin.collections.ArrayList

val hobbyList = listOf("Meditation", "Yoga", "Computer Programming", "Hacking", "3D Animation", "Story Telling", "Graphic Design", "Inventing")
val menuList = listOf("Preview", "Share", "Get Link")
val bombList = listOf("RDS-220", "B41", "TX-21")
val menuIconList = listOf(
    R.drawable.ic_baseline_visibility_24,
    R.drawable.ic_baseline_person_add_24,
    R.drawable.ic_baseline_link_24
)
val natureImageList = listOf(
    R.drawable.p1,
    R.drawable.p2,
    R.drawable.p3,
    R.drawable.p4,
    R.drawable.p5,
    R.drawable.p6,
    R.drawable.p7,
    R.drawable.p8,
    R.drawable.p9,
    R.drawable.p10,
    R.drawable.p11,
    R.drawable.p12,
    R.drawable.p13,
    R.drawable.p14,
    R.drawable.p15,
    R.drawable.p16,
    R.drawable.p17,
    R.drawable.p18,
    R.drawable.p19,
    R.drawable.p20,
    R.drawable.p21,
    R.drawable.p22,
    R.drawable.p23,
    R.drawable.p24,
    R.drawable.p25,
    R.drawable.p26,
)
val animeImageList = listOf(
    R.drawable.ani1,
    R.drawable.ani2,
    R.drawable.ani3,
    R.drawable.ani4,
    R.drawable.ani5,
    R.drawable.ani6,
    R.drawable.ani7,
    R.drawable.ani8,
    R.drawable.ani9,
    R.drawable.ani10,
    R.drawable.ani11,
    R.drawable.ani12,
    R.drawable.ani13,
    R.drawable.ani14,
    R.drawable.ani15,
    R.drawable.ani16,
    R.drawable.ani17,
    R.drawable.ani18,
    R.drawable.ani19,
    R.drawable.ani20,
    R.drawable.ani21,
    R.drawable.ani22,
    R.drawable.ani23,
    R.drawable.ani24,
    R.drawable.ani25,
    R.drawable.ani26,
    R.drawable.ani27,
    R.drawable.ani28,
    R.drawable.ani29,
    R.drawable.ani30,
    R.drawable.ani31,
    R.drawable.ani32,
    R.drawable.ani33,
    R.drawable.ani34,
    R.drawable.ani35,
    R.drawable.ani36,
    R.drawable.ani37,
    R.drawable.ani38,
    R.drawable.ani39
)

fun getNatureItemList(): List<Item> {
    val itemList = ArrayList<Item>()
    natureImageList.forEachIndexed { index, image ->
        itemList.add(
            Item(
                id = index + 1,
                title = "The beauty of nature ${index + 1}",
                subtitle = "I felt my lungs inflate with the onrush of scenery—air, mountains, trees, people.",
                author = "Person ${index + 1}",
                imageId = image
            )
        )
    }
    return itemList
}

fun getAnimeItemList(): List<Item> {
    val itemList = ArrayList<Item>()
    animeImageList.forEachIndexed { index, image ->
        itemList.add(
            Item(
                id = index + 1,
                title = "Anime ${index + 1}",
                subtitle = "A good anime has certain qualities that make it unique, whether it be a different plot point, characters, fight scenes, romance etc.",
                author = "Person ${index + 1}",
                imageId = image
            )
        )
    }
    return itemList
}

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

data class Item(
    val id: Int,
    val title: String,
    val subtitle: String,
    @DrawableRes val imageId: Int,
    val author: String,
    val source: String = "Local Source"
)

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
    TOP_APP_BAR(value = "Top App Bar"),
    BOTTOM_APP_BAR(value = "Bottom App Bar"),
    BOTTOM_NAVIGATION_BAR(value = "Bottom Navigation Bar"),
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