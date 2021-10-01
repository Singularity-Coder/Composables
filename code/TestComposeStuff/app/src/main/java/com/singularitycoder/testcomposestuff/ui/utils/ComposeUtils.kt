package com.singularitycoder.testcomposestuff.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.singularitycoder.testcomposestuff.ui.theme.AppColor

@Composable
fun Paper(stuff: @Composable ColumnScope.() -> Unit) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(start = 0.dp, top = 24.dp, end = 0.dp, bottom = 0.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            stuff.invoke(this)
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
    color = AppColor.Blue900,
    fontWeight = FontWeight.Black,
    modifier = Modifier.fillMaxWidth(),
    textDecoration = TextDecoration.None,
    style = MaterialTheme.typography.body1
)

// Anchor is missing
@Composable
fun SnackTime(
    showSnackbar: MutableState<Boolean> = remember { mutableStateOf(true) },
    message: String = "NA",
    btnText: String = "NA",
    btnAction: () -> Unit = {}
) {
    if (showSnackbar.value) {
        Snackbar(
            action = { Button(onClick = { btnAction.invoke() }) { Text(btnText) } },
            modifier = Modifier.padding(8.dp)
        ) { Text(text = message) }
    }
}

@Composable
fun RowScope.HorizontalSpace(spacing: Dp) = Spacer(modifier = Modifier.width(spacing))

@Composable
fun ColumnScope.VerticalSpace(spacing: Dp) = Spacer(modifier = Modifier.height(spacing))

// No neutral, no view but since each field returns unit, it may accept any num of items.
@Composable
fun SimpleAlertDialog(
    isCancellable: Boolean = false,
    showDialog: MutableState<Boolean>,
    title: String = "NA",
    message: String = "NA",
    positiveBtnText: String = "NA",
    negativeBtnText: String = "NA",
    positiveBtnAction: () -> Unit = {},
    negativeBtnAction: () -> Unit = {},
    dismissAction: () -> Unit = {}
) {
    @Composable
    fun Words(
        text: String,
        fontWeight: FontWeight
    ) = Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = fontWeight,
        color = AppColor.LightBlack,
        modifier = Modifier.fillMaxWidth()
    )

    @Composable
    fun Action(
        actionText: String,
        action: () -> Unit
    ) = Button(
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Transparent, contentColor = AppColor.Purple500),
        onClick = action
    ) { Text(actionText) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = !isCancellable
                dismissAction.invoke()
            },
            title = if ("NA" != title) {
                { Words(text = title, fontWeight = FontWeight.Bold) }
            } else null,
            text = if ("NA" != message) {
                { Words(text = message, fontWeight = FontWeight.Normal) }
            } else null,
            confirmButton = {
                if ("NA" != positiveBtnText) Action(actionText = positiveBtnText, action = {
                    positiveBtnAction.invoke()
                    showDialog.value = !isCancellable
                })
            },
            dismissButton = {
                if ("NA" != negativeBtnText) Action(actionText = negativeBtnText, action = {
                    negativeBtnAction.invoke()
                    showDialog.value = !isCancellable
                })
            }
        )
    }
}
