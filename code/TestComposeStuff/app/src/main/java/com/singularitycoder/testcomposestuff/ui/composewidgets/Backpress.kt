package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.activity.compose.BackHandler
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.utils.Board
import com.singularitycoder.testcomposestuff.utils.Composables

@Composable
fun ComposeBackPress() {
    Board(title = Composables.ON_BACKPRESS.value) {
        val backPressedCount = remember { mutableStateOf(0) }
        BackHandler(enabled = backPressedCount.value != 10, onBack = {
            backPressedCount.value += 1
        })
        Text(
            buildAnnotatedString {
                append("Back button was pressed ")
                withStyle(style = SpanStyle(color = Color.Magenta)) {
                    append("${backPressedCount.value}")
                }
                append(" times. Press it 10 times to exit the App!")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeBackPressPreview() = ComposablesApp { ComposeBackPress() }