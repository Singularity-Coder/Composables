package com.singularitycoder.testcomposestuff

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentTransaction
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.singularitycoder.testcomposestuff.ui.theme.AppColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.theme.PlayFairFamily
import com.singularitycoder.testcomposestuff.ui.theme.Ticket
import com.singularitycoder.testcomposestuff.ui.utils.*

/**
 * To avoid Modifier wrap -> Preferences -> Code Style -> Kotlin -> Compose (Tab) -> Enable Compose formatting for Modifiers (Uncheck this)
 * **/

// Accessing Value Resources
// Color Resource
// Drawable Resources
// String Resources

// Testing - Compose test rule, for content, for test case

// Drawer
// Custom Shapes - Generic, Rounded Corner, Cut Corner, corner based shape, Corner sizes
// Text Locales
// Fonts
// Alignment Line - Vertical, Horizontal
// Alignment
// Color Painter
// Image Painter
// Android Image Asset
// Table
// Theming
// Data Table
// Emphasis
// Surface
// Typography
// Icons
// Units - Dp, Px, IntPx, Size, TextUnit
// Previewing Components
// Handling State
// Alignment
// Font Resource
// Arrangement
// Border
// Android View - Embed Custom View, Ref, Theme


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
        val textFieldResult = remember { mutableStateOf("") }
        val cardResult = remember { mutableStateOf("") }
        val iconBtnResult = remember { mutableStateOf("") }
        val menuResult = remember { mutableStateOf("") }
        val switchResult = remember { mutableStateOf("") }
        val radioBtnResult = remember { mutableStateOf("") }
        val checkBoxResult = remember { mutableStateOf("") }

        SetStatusBarColor()

        Scaffold(scaffoldState = scaffoldState) {
            Column(modifier = Modifier.fillMaxSize().background(color = AppColor.Purple700)) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(start = 24.dp, end = 24.dp)) {

                    Text(
                        text = "Composables",
                        color = AppColor.White,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                        textDecoration = TextDecoration.None,
                        style = MaterialTheme.typography.h4
                    )

                    Board(title = "Alert Dialog", result = alertResult.value) {
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

                    Board(title = "Snackbar", result = snackbarResult.value) {
                        // Action on new line
                        // Rounded snack with background
                        // Snackbar with specific duration
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

                    Board(title = "Button", result = buttonResult.value) {
                        @Composable
                        fun Btn(text: String, roundness: Dp = 4.dp, action: () -> Unit) = Button(
                            modifier = Modifier.padding(start = 0.dp, top = 8.dp, end = 8.dp, bottom = 0.dp),
                            shape = RoundedCornerShape(size = roundness),
                            onClick = action
                        ) { Text(text = text) }
                        FlowRow {
                            Btn(text = "Default") { buttonResult.value = "Default Button Clicked" }
                            Btn(text = "Sharp", roundness = 0.dp) { buttonResult.value = "Sharp Button Clicked" }
                            Btn(text = "Semi-Rounded", roundness = 12.dp) { buttonResult.value = "Semi-Rounded Button Clicked" }
                            Btn(text = "Rounded", roundness = 24.dp) { buttonResult.value = "Rounded Button Clicked" }
                            DefaultButton(actionText = "Outlined") { buttonResult.value = "Outlined Button Clicked" }
                            TextButton(
                                modifier = Modifier.padding(start = 0.dp, top = 8.dp, end = 8.dp, bottom = 0.dp),
                                onClick = { buttonResult.value = "Text Button Clicked" }
                            ) { Text(text = "Text") }
                        }
                    }

                    Board(title = "Text") {
                        // Link Text - change text color on click
                        // Styling Text - Text Align, Baseline shift, Direction, Direction Algorithm, Geometric Transform, Indent, Overflow
                        // Fonts - add custom fonts, font weights, font families, font style, font synthesis, ResourceFont, FontListFontFamily
                        Text(text = stringResource(id = R.string.basic_text_view), modifier = Modifier.padding(top = 4.dp))
                        Text(text = "Align Text to the end", textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.Blue)) { append("M") }
                                append("ultiple ")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Black, color = Color.Red)) { append("S") }
                                append("tyles")
                            }
                        )
                        Text(text = "Justify this Text", textAlign = TextAlign.Justify, modifier = Modifier.fillMaxWidth()) // Not working
                        Text(text = "Custom font", fontFamily = PlayFairFamily, fontWeight = FontWeight.Bold)
                        Text(text = "Custom Text Size", fontSize = 8.sp)
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

                    Board(title = "Text Fields", result = textFieldResult.value) {
                        // Error Field
                        // Trialing & ErrorIcon
                        // Long Description field
                        // Default close icon func
                        // IME options, Keyboard Type, Input State, Visual Transformation, Password Field, Handle value changes, Default value, text field value
                        val textFieldText = remember { mutableStateOf("") }
                        val outlinedTextFieldText = remember { mutableStateOf("") }
                        val customTextFieldText = remember { mutableStateOf("") }
                        8.dp.VerticalSpace()
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            enabled = false,
                            label = { Text("Read Only Text Field") },
                            colors = TextFieldDefaults.textFieldColors(disabledIndicatorColor = Color.LightGray)
                        )
                        8.dp.VerticalSpace()
                        TextField(value = textFieldText.value, onValueChange = { it: String ->
                            textFieldResult.value = it
                            textFieldText.value = it
                        }, label = { Text("Enter Name") })
                        OutlinedTextField(value = outlinedTextFieldText.value, onValueChange = { it: String ->
                            textFieldResult.value = it
                            outlinedTextFieldText.value = it
                        }, label = { Text("Enter Name") })
                        8.dp.VerticalSpace()
                        val passwordVisibility = remember { mutableStateOf(false) }
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = customTextFieldText.value,
                            onValueChange = { it: String ->
                                textFieldResult.value = it
                                customTextFieldText.value = it
                            },
                            textStyle = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.SansSerif),
                            enabled = true,
                            isError = false,
                            singleLine = true,
                            maxLines = 1,
                            visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(autoCorrect = false, keyboardType = KeyboardType.Password),
                            label = { Text(text = "Enter Password", color = if (customTextFieldText.value.length > 10) AppColor.ErrorDark else AppColor.Purple700) },
                            leadingIcon = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(painter = painterResource(id = R.drawable.ic_outline_lock_24), contentDescription = "Password Placeholder Image")
                                }
                            },
                            trailingIcon = {
                                if (customTextFieldText.value.length > 10) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_baseline_error_24),
                                        contentDescription = "Warning",
                                        tint = AppColor.ErrorDark,
                                        modifier = Modifier.clickable {
                                            showFeedback(
                                                coroutineScope = coroutineScope,
                                                scaffoldState = scaffoldState,
                                                message = "Password cannot be more than 10 characters!",
                                                btnText = "Ok",
                                                duration = SnackbarDuration.Indefinite
                                            )
                                        }
                                    )
                                } else {
                                    val image = if (passwordVisibility.value) {
                                        painterResource(id = R.drawable.ic_baseline_visibility_off_24)
                                    } else painterResource(id = R.drawable.ic_baseline_visibility_24)
                                    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                                        Icon(painter = image, "Trailing Icon", tint = AppColor.Purple500)
                                    }
                                }
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = AppColor.TitleColor,
                                backgroundColor = if (customTextFieldText.value.length > 10) AppColor.ErrorLight else AppColor.Purple100,
                                focusedIndicatorColor = if (customTextFieldText.value.length > 10) AppColor.ErrorDark else AppColor.Purple700,
                                unfocusedIndicatorColor = AppColor.Purple700,
                                disabledIndicatorColor = AppColor.Purple200,
                                errorIndicatorColor = AppColor.ErrorDark
                            )
                        )
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            @Composable
                            fun Words(text: String, modifier: Modifier = Modifier.fillMaxWidth(), textAlign: TextAlign = TextAlign.End) {
                                Text(
                                    text = text,
                                    fontSize = 12.sp,
                                    color = if (customTextFieldText.value.length > 10) AppColor.ErrorDark else Color.Gray,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    modifier = modifier,
                                    textAlign = textAlign
                                )
                            }
                            if (customTextFieldText.value.length > 10) Words(text = "Exceeded character limit!", modifier = Modifier.wrapContentWidth(), textAlign = TextAlign.Start)
                            Words(text = "${customTextFieldText.value.length}/10")
                        }
                    }

                    Board(title = "Card") {
                        @Composable
                        fun Board(
                            text: String,
                            textPadding: Dp = 8.dp,
                            elevation: Dp = 1.dp,
                            shape: Shape = RoundedCornerShape(size = 4.dp),
                            backgroundColor: Color = Color.White,
                            border: BorderStroke? = null,
                            contentColor: Color = Color.Unspecified
                        ) = Card(
                            modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                            elevation = elevation,
                            shape = shape,
                            backgroundColor = backgroundColor,
                            border = border,
                            contentColor = contentColor
                        ) { Text(text = text, modifier = Modifier.padding(textPadding)) }
                        FlowRow {
                            Board(text = "Basic Card")
                            Board(text = "Elevated Card", elevation = 10.dp)
                            Board(text = "Rounded Card\n\n\n\n", elevation = 4.dp, shape = RoundedCornerShape(size = 16.dp), textPadding = 16.dp)
                            Board(text = "Cut Card\n\n\n\n", elevation = 4.dp, shape = CutCornerShape(size = 16.dp), textPadding = 16.dp)
                            Board(text = "Colorful Card", backgroundColor = Color.Yellow)
                            Board(text = "Bordered Card", border = BorderStroke(1.dp, color = AppColor.Purple500))
                            Board(text = "Card with Content Color", contentColor = Color.Magenta)
                        }
                    }

                    Board(title = "Custom Shapes") {
                        8.dp.VerticalSpace()
                        Ticket(modifier = Modifier)
                    }

                    Board(title = "Icon Button", result = iconBtnResult.value) {
                        8.dp.VerticalSpace()
                        FlowRow {
                            IconButton(onClick = { iconBtnResult.value = "You poked the eye! Arrrrrrrrgh..." }) {
                                Icon(painter = painterResource(id = R.drawable.ic_baseline_visibility_24), "Basic Icon Button", tint = AppColor.Purple500)
                            }
                            8.dp.HorizontalSpace()
                            IconButton(onClick = { iconBtnResult.value = "You sent something. I know what that is. Huhuhahahaha..." }) {
                                Icon(painter = painterResource(id = R.drawable.ic_baseline_send_24), "Basic Icon Button", tint = Color.Green)
                            }
                            8.dp.HorizontalSpace()
                            IconButton(onClick = { iconBtnResult.value = "\"ALL DAY GAMING\" mode activated!" }) {
                                Icon(painter = painterResource(id = R.drawable.ic_baseline_videogame_asset_24), "Basic Icon Button", tint = Color.Blue)
                            }
                            8.dp.HorizontalSpace()
                            IconButton(onClick = { iconBtnResult.value = "Gotta kill 'em all!" }) {
                                Icon(painter = painterResource(id = R.drawable.ic_baseline_catching_pokemon_24), "Basic Icon Button", tint = AppColor.Teal200)
                            }
                            8.dp.HorizontalSpace()
                            IconButton(onClick = { iconBtnResult.value = "Starting World War 3! Please wait..." }) {
                                Icon(painter = painterResource(id = R.drawable.ic_baseline_coronavirus_24), "Basic Icon Button", tint = Color.Red)
                            }
                        }
                    }

                    // https://webneel.com/best-panoramic-photography
                    Board(title = "Images") {
                        // Rounded Corner Image
                        // Cut Corners
                        // Circular Shape
                        8.dp.VerticalSpace()
                        Image(painter = painterResource(id = R.drawable.pan1), contentDescription = null)
                        8.dp.VerticalSpace()
                        Image(painter = painterResource(id = R.drawable.pan7), contentDescription = null, alpha = 0.2F, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                        8.dp.VerticalSpace()
                        Image(painter = painterResource(id = R.drawable.pan2), contentDescription = null, modifier = Modifier.clip(RoundedCornerShape(16.dp)))
                        8.dp.VerticalSpace()
                        Image(painter = painterResource(id = R.drawable.pan3), contentDescription = null, modifier = Modifier.clip(CutCornerShape(16.dp)))
                        8.dp.VerticalSpace()
                        Image(painter = painterResource(id = R.drawable.pan5), contentDescription = null, modifier = Modifier.clip(CircleShape))
                        8.dp.VerticalSpace()
                        Image(painter = painterResource(id = R.drawable.pic1), contentDescription = null, modifier = Modifier.clip(CircleShape).height(64.dp).width(64.dp))
                        8.dp.VerticalSpace()
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null)
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.ColorDodge))
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.ColorBurn))
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Color))
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Darken))
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Difference))
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Lighten))
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Luminosity))
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Multiply))
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Overlay))
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Saturation))
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Screen))
                        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Softlight))
                        8.dp.VerticalSpace()
                        Image(painter = painterResource(id = R.drawable.pan6), contentDescription = null)
                        Image(painter = painterResource(id = R.drawable.pan6), contentDescription = null, colorFilter = ColorFilter.lighting(Color.Yellow, Color.Blue))
                        Image(painter = painterResource(id = R.drawable.pan6), contentDescription = null, colorFilter = ColorFilter.lighting(Color.Green, Color.Red))
                        Image(painter = painterResource(id = R.drawable.pan6), contentDescription = null, colorFilter = ColorFilter.lighting(Color.Green, Color.Black))
                    }

                    Board(title = "Gestures") {
                        // Long Press
                        // Double Tap
                        // Tap
                        // Drag
                        FlowRow {

                        }
                    }

                    Board(title = "Animations") {
                        // Single Value Animation
                        // Crossfade animation
                        // Snap Animations
                        // Tween Animations
                        // Vector animations
                        // Animated Values
                        // Spring Animations
                        FlowRow {

                        }
                    }

                    Board(title = "Menus", result = menuResult.value) {
                        FlowRow {
                            val showSimpleMenu = remember { mutableStateOf(false) }
                            val showMenuWithImage = remember { mutableStateOf(false) }

                            DefaultButton(actionText = "Simple Menu") { showSimpleMenu.value = true }
                            DropdownMenu(expanded = showSimpleMenu.value, onDismissRequest = { showSimpleMenu.value = false }) {
                                hobbyList.forEachIndexed { index, value ->
                                    DropdownMenuItem(onClick = {
                                        showSimpleMenu.value = false
                                        menuResult.value = "You clicked on ${hobbyList[index]}!"
                                    }) { Text(text = value) }
                                }
                            }
                            DefaultButton(actionText = "Menu With Icons") { showMenuWithImage.value = true }
                            DropdownMenu(expanded = showMenuWithImage.value, modifier = Modifier.fillMaxWidth(), onDismissRequest = { showMenuWithImage.value = false }) {
                                menuList.forEachIndexed { index, value ->
                                    DropdownMenuItem(onClick = {
                                        showMenuWithImage.value = false
                                        menuResult.value = "You clicked on ${menuList[index]}!"
                                    }) {
                                        Icon(painter = painterResource(id = menuIconList[index]), "Menu Icon", tint = AppColor.Purple500)
                                        16.dp.HorizontalSpace()
                                        Text(text = value, maxLines = 1)
                                    }
                                }
                            }
                        }
                    }

                    Board(title = "Progress Bars") {
                        // Switching btw determiate to indetreminate and vice versa
                        // Setting custom colors
                        // Progress styles
                        val progress = remember { mutableStateOf(0.5f) }
                        val animatedProgress = animateFloatAsState(targetValue = progress.value, animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec).value

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            8.dp.VerticalSpace()
                            Text("Indeterminate Progress Bar", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
                            8.dp.VerticalSpace()
                            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                            8.dp.VerticalSpace()
                            CircularProgressIndicator()
                            24.dp.VerticalSpace()
                            Text("Determinate Progress Bar", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
                            8.dp.VerticalSpace()
                            LinearProgressIndicator(progress = animatedProgress, modifier = Modifier.fillMaxWidth())
                            8.dp.VerticalSpace()
                            CircularProgressIndicator(progress = animatedProgress)
                        }

                        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                            IconButton(onClick = { if (progress.value > 0f) progress.value -= 0.1f }, modifier = Modifier.wrapContentWidth()) {
                                Icon(painter = painterResource(id = R.drawable.ic_baseline_remove_circle_outline_24), "Decrease Progress", tint = AppColor.Purple500)
                            }
                            8.dp.HorizontalSpace()
                            IconButton(onClick = { if (progress.value < 1f) progress.value += 0.1f }, modifier = Modifier.wrapContentWidth()) {
                                Icon(painter = painterResource(id = R.drawable.ic_baseline_add_circle_outline_24), "Increase Progress", tint = AppColor.Purple500)
                            }
                        }
                    }

                    Board(title = "Switch", result = switchResult.value) {
                        8.dp.VerticalSpace()
                        val checkedState = remember { mutableStateOf(true) }
                        Switch(
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = it
                                switchResult.value = if (it) "ON" else "OFF"
                            }
                        )
                    }

                    Board(title = "Checkbox", result = checkBoxResult.value) {
                        // Tristate Checkbox
                        8.dp.VerticalSpace()
                        val checkedState = remember { mutableStateOf(true) }
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = it
                                checkBoxResult.value = if (it) "Checked" else "Not Checked"
                            }
                        )
                    }

                    Board(title = "Radio Button", result = radioBtnResult.value) {
                        // Radio Group
                        val (selectedOption, onOptionSelected) = remember { mutableStateOf(bombList[0]) }
                        fun onClick(bomb: String) {
                            onOptionSelected(bomb)
                            radioBtnResult.value = "$selectedOption selected!"
                        }
                        // FIXME
                        bombList.forEach { bomb: String ->
                            8.dp.VerticalSpace()
                            Row(modifier = Modifier.fillMaxWidth().selectable(selected = bomb == selectedOption, onClick = { onClick(bomb) })) {
                                RadioButton(selected = bomb == selectedOption, onClick = { onClick(bomb) })
                                16.dp.HorizontalSpace()
                                Text(text = bomb)
                            }
                        }
                    }

                    Board(title = "Slider") {
                        val sliderPosition = remember { mutableStateOf(0f) }
                        Text(text = sliderPosition.value.toString(), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
                        Slider(value = sliderPosition.value, onValueChange = { sliderPosition.value = it })
                    }

                    Board(title = "Lists") {
                        FlowRow {

                        }
                    }

                    Board(title = "Floating Action Button") {
                        FlowRow {
                            8.dp.VerticalSpace()
                            FloatingActionButton(
                                modifier = Modifier.align(Alignment.End).padding(12.dp),
                                onClick = {}
                            ) { Text("+") }
                        }
                    }

                    Board(title = "Extended Floating Action Button") {
                        FlowRow {

                        }
                    }

                    Board(title = "Scaffold") {
                        FlowRow {

                        }
                    }

                    Board(title = "Bottom Navigation Bar") {
                        FlowRow {

                        }
                    }

                    Board(title = "Top App Bar") {
                        FlowRow {

                        }
                    }

                    Board(title = "Bottom App Bar") {
                        FlowRow {

                        }
                    }

                    Board(title = "Bottom Navigation Item") {
                        FlowRow {

                        }
                    }

                    Board(title = "Backdrop Scaffold") {
                        FlowRow {

                        }
                    }

                    Board(title = "Tab Row") {
                        FlowRow {

                        }
                    }

                    Board(title = "Tab") {
                        FlowRow {

                        }
                    }

                    Board(title = "Scrollable Tab Row") {
                        FlowRow {

                        }
                    }

                    Board(title = "Divider") {
                        FlowRow {

                        }
                    }

                    Board(title = "Stack") {
                        FlowRow {

                        }
                    }

                    Board(title = "FlowRow") {
                        FlowRow {

                        }
                    }

                    Board(title = "FlowColumn") {
                        FlowRow {

                        }
                    }

                    Board(title = "Scrollable Row") {
                        FlowRow {

                        }
                    }

                    Board(title = "Scrollable Column") {
                        FlowRow {

                        }
                    }

                    Board(title = "Constraint Layout") {
                        FlowRow {

                        }
                    }

                    Board(title = "Box Layout") {
                        FlowRow {
                            // Stack elements
                            Box(modifier = Modifier.fillMaxSize()) {
                                Card(backgroundColor = Color.Black, modifier = Modifier.padding(all = 16.dp)) { Text(text = "Black") }
                                32.dp.HorizontalSpace()
                                Card(backgroundColor = Color.Magenta, modifier = Modifier.padding(all = 16.dp)) { Text(text = "Megenta") }
                                64.dp.HorizontalSpace()
                                Card(backgroundColor = Color.Yellow, modifier = Modifier.padding(all = 16.dp)) { Text(text = "Yellow") }
                            }
                            BoxWithConstraints {
                                val boxWithConstraintsScope = this
                                // You can use this scope to get the minWidth, maxWidth, minHeight, maxHeight in dp and constraints
                                Column {
                                    if (boxWithConstraintsScope.maxHeight >= 200.dp) {
                                        Text("This is only visible when the maxHeight is >= 200.dp")
                                    }
                                    Text("minHeight: ${boxWithConstraintsScope.minHeight}, maxHeight: ${boxWithConstraintsScope.maxHeight},  minWidth: ${boxWithConstraintsScope.minWidth} maxWidth: ${boxWithConstraintsScope.maxWidth}")
                                }
                            }
                        }
                    }

                    24.dp.VerticalSpace()
                }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() = ComposablesApp {}