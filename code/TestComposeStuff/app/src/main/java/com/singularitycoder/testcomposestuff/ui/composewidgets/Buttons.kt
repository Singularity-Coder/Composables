package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.singularitycoder.testcomposestuff.ui.theme.AppColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.Board
import com.singularitycoder.testcomposestuff.ui.utils.Composables
import com.singularitycoder.testcomposestuff.ui.utils.DefaultButton

@Composable
fun ComposeButtons() {
    val buttonResult = remember { mutableStateOf("") }
    Board(title = Composables.BUTTON.value, result = buttonResult.value, titleBottomPadding = false) {
        FlowRow {
            @Composable
            fun Btn(
                text: String,
                roundness: Dp = 4.dp,
                isEnabled: Boolean = true,
                colors: ButtonColors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary, contentColor = AppColor.White),
                action: () -> Unit
            ) = Button(
                modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                shape = RoundedCornerShape(size = roundness),
                enabled = isEnabled,
                colors = colors,
                onClick = action
            ) { Text(text = text) }
            Btn(text = "Default") { buttonResult.value = "Default Button Clicked" }
            Btn(text = "Sharp", roundness = 0.dp) { buttonResult.value = "Sharp Button Clicked" }
            Btn(text = "Semi-Rounded", roundness = 12.dp) { buttonResult.value = "Semi-Rounded Button Clicked" }
            Btn(text = "Rounded", roundness = 24.dp) { buttonResult.value = "Rounded Button Clicked" }
            Btn(text = "Disabled", isEnabled = false) {}
            Btn(text = "Colored", colors = ButtonDefaults.buttonColors(backgroundColor = AppColor.Teal500, contentColor = AppColor.Teal900)) {
                buttonResult.value = "Colored Button Clicked"
            }

            @Composable
            fun OutlineBtn(
                text: String,
                roundness: Dp = 4.dp,
                isEnabled: Boolean = true,
                colors: ButtonColors = ButtonDefaults.outlinedButtonColors(backgroundColor = AppColor.Transparent, contentColor = AppColor.Purple500),
                action: () -> Unit
            ) = OutlinedButton(
                modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                shape = RoundedCornerShape(size = roundness),
                enabled = isEnabled,
                colors = colors,
                onClick = action
            ) { Text(text = text) }
            OutlineBtn(text = "Outline") { buttonResult.value = "Outline Button Clicked" }
            OutlineBtn(text = "Disabled Outline", isEnabled = false) { }
            OutlineBtn(text = "Colored Outline", colors = ButtonDefaults.outlinedButtonColors(contentColor = AppColor.Teal700)) {
                buttonResult.value = "Colored Outline Button Clicked"
            }
            DefaultButton(actionText = "Rounded Outline") { buttonResult.value = "Rounded Outline Button Clicked" }

            @Composable
            fun IconBtn(
                text: String,
                imageVector: ImageVector,
                isStartIcon: Boolean = true,
                action: () -> Unit
            ) = Button(
                modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                onClick = action
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (!isStartIcon) Text(text = text)
                    Icon(
                        imageVector = imageVector,
                        contentDescription = null,
                        modifier = if (isStartIcon) Modifier.padding(end = 8.dp) else Modifier.padding(start = 8.dp)
                    )
                    if (isStartIcon) Text(text = text)
                }
            }
            IconBtn(text = "Icon Btn", imageVector = Icons.Default.FavoriteBorder) { buttonResult.value = "Start Icon Button Clicked" }
            IconBtn(text = "Icon Btn", imageVector = Icons.Default.Share, isStartIcon = false) { buttonResult.value = "End Icon Button Clicked" }

            val horizontalGradient = Brush.horizontalGradient(
                colors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary),
                startX = 0f,
                endX = 250f
            )
            Button(
                modifier = Modifier.padding(top = 8.dp, end = 8.dp).background(brush = horizontalGradient).clip(RoundedCornerShape(4.dp)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AppColor.Transparent,
                    contentColor = AppColor.White,
                    disabledBackgroundColor = AppColor.Transparent,
                    disabledContentColor = AppColor.Transparent
                ),
                shape = RoundedCornerShape(size = 4.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
                onClick = { buttonResult.value = "Horizontal Gradient Button Clicked" }
            ) { Text(text = "Horizontal Gradient") }

            val verticalGradient = Brush.verticalGradient(
                colors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary),
                startY = 0f,
                endY = 100f
            )
            Text(
                text = "Vertical Gradient",
                color = AppColor.White,
                modifier = Modifier
                    .padding(top = 8.dp, end = 8.dp)
                    .clickable(onClick = { buttonResult.value = "Vertical Gradient Button Clicked" })
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush = verticalGradient)
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = 1.sp
            )

            @Composable
            fun TextBtn(
                text: String,
                roundness: Dp = 4.dp,
                isEnabled: Boolean = true,
                colors: ButtonColors = ButtonDefaults.textButtonColors(backgroundColor = AppColor.Transparent, contentColor = AppColor.Purple500),
                action: () -> Unit
            ) = TextButton(
                modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                shape = RoundedCornerShape(size = roundness),
                enabled = isEnabled,
                colors = colors,
                onClick = action
            ) { Text(text = text) }
            TextBtn(text = "Text") { buttonResult.value = "Text Button Clicked" }
            TextBtn(text = "Disabled Text", isEnabled = false) {}
            TextBtn(text = "Colored Text", colors = ButtonDefaults.outlinedButtonColors(contentColor = AppColor.Teal900)) {
                buttonResult.value = "Colored Text Button Clicked"
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeButtonsPreview() = ComposablesApp { ComposeButtons() }