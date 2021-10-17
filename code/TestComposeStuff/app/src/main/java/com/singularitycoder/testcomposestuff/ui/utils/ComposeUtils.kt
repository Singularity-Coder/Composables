package com.singularitycoder.testcomposestuff.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.singularitycoder.testcomposestuff.ui.theme.AppColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SetStatusBarColor() {
    // https://stackoverflow.com/questions/65610216/how-to-change-statusbar-color-in-jetpack-compose
    val systemUiController = rememberSystemUiController()
    if (isSystemInDarkTheme()) systemUiController.setSystemBarsColor(color = AppColor.Transparent)
    else systemUiController.setSystemBarsColor(color = AppColor.Purple700)
}

@Composable
fun Board(title: String, result: String = "", titleBottomPadding: Boolean = true, stuff: @Composable ColumnScope.() -> Unit) {
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(start = 0.dp, top = 24.dp, end = 0.dp, bottom = 0.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            Words(text = title)
            if (titleBottomPadding) 8.dp.VerticalSpace()
            stuff.invoke(this)
            if (!result.isNullOrBlankOrNaOrNullString()) Result(result = "Result: \n$result")
        }
    }
}

@Composable
fun DefaultButton(actionText: String, action: () -> Unit) = Button(
    modifier = Modifier.padding(start = 0.dp, top = 8.dp, end = 8.dp, bottom = 0.dp),
    shape = RoundedCornerShape(size = 24.dp),
    border = BorderStroke(width = 1.dp, color = AppColor.Purple500),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = AppColor.Transparent,
        contentColor = AppColor.Purple500,
        disabledBackgroundColor = AppColor.Transparent,
        disabledContentColor = AppColor.Purple200
    ),
    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
    onClick = { action.invoke() }
) { Text(text = actionText) }

@Composable
fun Words(text: String) = Text(
    text = text,
    color = AppColor.TitleColor,
    fontWeight = FontWeight.Medium,
    modifier = Modifier.fillMaxWidth(),
    textDecoration = TextDecoration.None,
    style = MaterialTheme.typography.body1
)

@Composable
fun Result(result: String) = Text(
    text = result,
    color = AppColor.SubtitleColor,
    fontWeight = FontWeight.Normal,
    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
    style = MaterialTheme.typography.caption
)

// Anchor is just setting this at that place. How do we show it at the bottom?
@Composable
fun Feedback(
    message: String = "NA",
    btnText: String = "NA",
    actionOnNewLine: Boolean = false,
    shape: Shape = MaterialTheme.shapes.small,
    btnAction: () -> Unit = {}
) = Snackbar(
    action = {
        if ("NA" == btnText) return@Snackbar
        TextButton(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppColor.Transparent,
                contentColor = AppColor.Purple200,
                disabledBackgroundColor = AppColor.Transparent,
                disabledContentColor = AppColor.Transparent
            ),
            onClick = { btnAction.invoke() }
        ) { Text(btnText) }
    },
    modifier = Modifier.padding(top = 8.dp),
    actionOnNewLine = actionOnNewLine,
    shape = shape
) { Text(text = message) }

@Composable
fun Dp.HorizontalSpace() = Spacer(modifier = Modifier.width(this))

@Composable
fun Dp.VerticalSpace() = Spacer(modifier = Modifier.height(this))

// No neutral, no view but since each field returns unit, it may accept any num of items.
@Composable
fun SimpleAlertDialog(
    title: String = "NA",
    message: String = "NA",
    positiveBtnText: String = "NA",
    negativeBtnText: String = "NA",
    positiveBtnAction: () -> Unit = {},
    negativeBtnAction: () -> Unit = {},
    dismissAction: () -> Unit = {}
) {
    @Composable
    fun Words(text: String, fontWeight: FontWeight) = Text(
        text = text,
        maxLines = 8,
        fontSize = 18.sp,
        fontWeight = fontWeight,
        color = AppColor.LightBlack,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.fillMaxWidth()
    )

    @Composable
    fun AlertBtn(actionText: String, action: () -> Unit) = Button(
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Transparent, contentColor = AppColor.Purple500),
        onClick = action
    ) { Text(actionText) }

    AlertDialog(
        shape = RoundedCornerShape(16.dp),
        onDismissRequest = dismissAction,
        title = if ("NA" != title) {
            { Words(text = title, fontWeight = FontWeight.Bold) }
        } else null,
        text = if ("NA" != message) {
            { Words(text = message, fontWeight = FontWeight.Normal) }
        } else null,
        confirmButton = {
            if ("NA" != positiveBtnText) AlertBtn(actionText = positiveBtnText, action = {
                positiveBtnAction.invoke()
            })
        },
        dismissButton = {
            if ("NA" != negativeBtnText) AlertBtn(actionText = negativeBtnText, action = {
                negativeBtnAction.invoke()
            })
        }
    )
}
