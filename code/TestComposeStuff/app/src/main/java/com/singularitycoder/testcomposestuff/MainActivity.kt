package com.singularitycoder.testcomposestuff

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentTransaction
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.singularitycoder.testcomposestuff.ui.theme.AppColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val CUSTOM_ALERT_DIALOG = "CUSTOM_ALERT_DIALOG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposablesApp { Surface(color = MaterialTheme.colors.background) { UI() } } }
    }

    @Composable
    private fun UI() {
        val scaffoldState = rememberScaffoldState() // You cannot reference directly composables in a non-compose location but you can refer their instances
        val coroutineScope = rememberCoroutineScope()

        val showAlert = remember { mutableStateOf(false) }
        val showScopedSnackbar = remember { mutableStateOf(false) }

        val alertResult = remember { mutableStateOf("") }
        val snackbarResult = remember { mutableStateOf("") }
        val buttonResult = remember { mutableStateOf("") }
        val textResult = remember { mutableStateOf("") }
        val cardResult = remember { mutableStateOf("") }

        SetStatusBarColor()

        Scaffold(scaffoldState = scaffoldState) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = AppColor.Purple700)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 24.dp, end = 24.dp)
                ) {

                    Text(
                        text = "Composables",
                        color = AppColor.White,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        textDecoration = TextDecoration.None,
                        style = MaterialTheme.typography.h4
                    )

                    Page(title = "Alert Dialog", result = alertResult.value) {
                        FlowRow {
                            DefaultButton(actionText = "Simple Dialog") { showAlert.value = true }
                            if (showAlert.value) {
                                SimpleAlertDialog(
                                    title = "Simple Alert Dialog",
                                    message = getString(R.string.long_message),
                                    positiveBtnText = "Ok",
                                    negativeBtnText = "Cancel",
                                    positiveBtnAction = {
                                        alertResult.value = "You read it!"
                                        showAlert.value = false
                                    },
                                    negativeBtnAction = {
                                        alertResult.value = "You didn't read it!"
                                        showAlert.value = false
                                    },
                                    dismissAction = {
                                        alertResult.value = "You dismissed the alert!"
                                        showAlert.value = false // If false then dialog cancels on touch of scrim
                                    }
                                )
                            }
                            DefaultButton(actionText = "Dialog Fragment") {
                                val fragmentTransaction = this@MainActivity.supportFragmentManager.beginTransaction().apply {
                                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    val previousFragment = this@MainActivity.supportFragmentManager.findFragmentByTag(CUSTOM_ALERT_DIALOG)
                                    if (null != previousFragment) remove(previousFragment)
                                    addToBackStack(null)
                                }
                                CustomDialogFragment(
                                    title = "Custom Dialog Fragment",
                                    message = getString(R.string.long_message),
                                    imgContentDesc = this@MainActivity.getString(R.string.app_name),
                                    positiveBtnText = "Ok",
                                    negativeBtnText = "Cancel",
                                    image = R.drawable.fight,
                                    positiveBtnAction = { alertResult.value = "You read it!" },
                                    negativeBtnAction = { alertResult.value = "You didn't read it!" }
                                ).show(fragmentTransaction, CUSTOM_ALERT_DIALOG)
                            }
                        }
                    }

                    Page(title = "Snackbar", result = snackbarResult.value) {
                        // Text
                        // With Action
                        // Action on new line
                        // Rounded snack with background
                        // Snackbar with common durations
                        // Snackbar with specific duration
                        FlowRow {
                            DefaultButton(actionText = "Snackbar") { showScopedSnackbar.value = true }
                            if (showScopedSnackbar.value) Feedback("I am a scoped Snackbar", btnText = "Ok") {
                                showScopedSnackbar.value = false
                                snackbarResult.value = "Something happened"
                            }
                            DefaultButton(actionText = "Simple Snackbar") {
                                Feedback(
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
                                Feedback(
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

                    Page(title = "Button", result = buttonResult.value) {
                        // Default Button
                        // Square Button
                        // Semi-Rounded Button
                        // Rounded Button
                        // Outlined Button
                        // Text Button
                        @Composable
                        fun Btn(type: String, roundness: Dp = 4.dp, action: () -> Unit) {
                            Button(
                                modifier = Modifier.padding(start = 0.dp, top = 8.dp, end = 8.dp, bottom = 0.dp),
                                shape = RoundedCornerShape(size = roundness),
                                onClick = action
                            ) { Text(text = type) }
                        }
                        FlowRow {
                            Btn(type = "Default") { buttonResult.value = "Default Button Clicked" }
                            Btn(type = "Sharp", roundness = 0.dp) { buttonResult.value = "Sharp Button Clicked" }
                            Btn(type = "Semi-Rounded", roundness = 12.dp) { buttonResult.value = "Semi-Rounded Button Clicked" }
                            Btn(type = "Rounded", roundness = 24.dp) { buttonResult.value = "Rounded Button Clicked" }
                            DefaultButton(actionText = "Outlined") { buttonResult.value = "Outlined Button Clicked" }
                            TextButton(
                                modifier = Modifier.padding(start = 0.dp, top = 8.dp, end = 8.dp, bottom = 0.dp),
                                onClick = { buttonResult.value = "Text Button Clicked" }
                            ) { Text(text = "Text") }
                        }
                    }

                    Page(title = "Text") {
                        // Basic Text View
                        // Different font families
                        // Different font sizes - h1 to body
                        // Different colors
                        // Different font weights - black, heavy, thin etc
                        // Different font styles - underlines, strikethrough, etc
                        // Letter spacing
                        // Text Background
                        Text(text = "Basic Text View", modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Colored Text View", color = Color.Magenta, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Thick Text", fontWeight = FontWeight.Black, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Thin Text", fontWeight = FontWeight.Thin, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Styled Text", fontStyle = FontStyle.Italic, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Monospace font style", fontFamily = FontFamily.Monospace, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "SanSerif font style", fontFamily = FontFamily.SansSerif, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Serif font style", fontFamily = FontFamily.Serif, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Cursive font style", fontFamily = FontFamily.Cursive, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Underlined Text", textDecoration = TextDecoration.Underline, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Strikeout Text", textDecoration = TextDecoration.LineThrough, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Letter Spacing", letterSpacing = 8.sp, modifier = Modifier.padding(top = 4.dp))
                        Surface(color = Color.Cyan) { Text(text = "Background Color", modifier = Modifier.padding(top = 4.dp)) }
                        Text(text = "Overline", style = MaterialTheme.typography.overline, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Caption", style = MaterialTheme.typography.caption, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Subtitle2", style = MaterialTheme.typography.subtitle2, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Subtitle1", style = MaterialTheme.typography.subtitle1, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "H6", style = MaterialTheme.typography.h6, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "H5", style = MaterialTheme.typography.h5, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "H4", style = MaterialTheme.typography.h4, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "H3", style = MaterialTheme.typography.h3, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "H2", style = MaterialTheme.typography.h2, modifier = Modifier.padding(top = 4.dp))
                        Text(text = "H1", style = MaterialTheme.typography.h1, modifier = Modifier.padding(top = 4.dp))
                    }

                    Page(title = "Card") {
                        FlowRow {

                        }
                    }

                    Page(title = "Floating Action Button") {
                        FlowRow {

                        }
                    }

                    Page(title = "Extended Floating Action Button") {
                        FlowRow {

                        }
                    }

                    Page(title = "Scaffold") {
                        FlowRow {

                        }
                    }

                    Page(title = "Bottom Navigation Bar") {
                        FlowRow {

                        }
                    }

                    Page(title = "Top App Bar") {
                        FlowRow {

                        }
                    }

                    Page(title = "Bottom Navigation Item") {
                        FlowRow {

                        }
                    }

                    Page(title = "Backdrop Scaffold") {
                        FlowRow {

                        }
                    }

                    Page(title = "Bottom App Bar") {
                        FlowRow {

                        }
                    }

                    Page(title = "Progress") {
                        FlowRow {

                        }
                    }

                    Page(title = "Tab Row") {
                        FlowRow {

                        }
                    }

                    Page(title = "Tab") {
                        FlowRow {

                        }
                    }

                    Page(title = "Scrollable Tab Row") {
                        FlowRow {

                        }
                    }

                    Page(title = "Divider") {
                        FlowRow {

                        }
                    }

                    Page(title = "Switch") {
                        FlowRow {

                        }
                    }

                    Page(title = "Checkbox") {
                        FlowRow {

                        }
                    }

                    Page(title = "Radio Button") {
                        FlowRow {

                        }
                    }

                    Page(title = "Slider") {
                        FlowRow {

                        }
                    }

                    Page(title = "Icon Button") {
                        FlowRow {

                        }
                    }

                    24.dp.VerticalSpace()
                }
            }
        }
    }

    @Composable
    private fun SetStatusBarColor() {
        // https://stackoverflow.com/questions/65610216/how-to-change-statusbar-color-in-jetpack-compose
        val systemUiController = rememberSystemUiController()
        if (isSystemInDarkTheme()) systemUiController.setSystemBarsColor(color = AppColor.Transparent)
        else systemUiController.setSystemBarsColor(color = AppColor.Purple700)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() = ComposablesApp {}