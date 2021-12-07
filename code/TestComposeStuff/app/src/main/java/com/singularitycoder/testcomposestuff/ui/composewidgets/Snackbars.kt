package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.flowlayout.FlowRow
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.utils.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun ComposeSnackbars(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    val snackbarResult = remember { mutableStateOf("") }
    Board(title = Composables.SNACKBAR.value, result = snackbarResult.value, titleBottomPadding = false) {
        // Action on new line
        // Rounded snack with background
        // Snackbar with specific duration
        val showScopedSnackbar = remember { mutableStateOf(false) }
        FlowRow {
            DefaultButton(actionText = "Snackbar") { showScopedSnackbar.value = true }
            if (showScopedSnackbar.value) Feedback("I am a scoped Snackbar", btnText = "Ok") {
                showScopedSnackbar.value = false
                snackbarResult.value = "Something happened"
            }
            DefaultButton(actionText = "Simple Snackbar") {
                showFeedback(
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState,
                    message = "Snackbar with action",
                    duration = SnackbarDuration.Short,  // Short is 4 sec, Long is 10 sec
                    dismissAction = {
                        showScopedSnackbar.value = false
                        snackbarResult.value = "Snackbar Dismissed"
                    }
                )
            }
            DefaultButton(actionText = "Snackbar with Action") {
                showFeedback(
                    coroutineScope = coroutineScope,
                    scaffoldState = scaffoldState,
                    message = "Snackbar with action",
                    btnText = "Ok",
                    btnAction = {
                        showScopedSnackbar.value = false
                        snackbarResult.value = "\"Ok\" Clicked"
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeSnackbarsPreview() = ComposablesApp { ComposeSnackbars() }