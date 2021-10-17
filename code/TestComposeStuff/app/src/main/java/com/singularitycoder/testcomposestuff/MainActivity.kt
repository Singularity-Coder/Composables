package com.singularitycoder.testcomposestuff

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.flowlayout.FlowRow
import com.singularitycoder.testcomposestuff.ui.composewidgets.*
import com.singularitycoder.testcomposestuff.ui.theme.AppColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.Board
import com.singularitycoder.testcomposestuff.ui.utils.Composables.*
import com.singularitycoder.testcomposestuff.ui.utils.HorizontalSpace
import com.singularitycoder.testcomposestuff.ui.utils.SetStatusBarColor
import com.singularitycoder.testcomposestuff.ui.utils.VerticalSpace
import kotlinx.coroutines.launch

/**
 * To avoid Modifier wrap -> Preferences -> Code Style -> Kotlin -> Compose (Tab) -> Enable Compose formatting for Modifiers (Uncheck this)
 * **/

// PHASE 2
// Animations
// Gestures
// Horizontal and vertical Lists with Search - Maybe News or Github API
// Horizontal and vertical Grid Views with Search - Maybe News or Github API
// Navigation components

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposablesApp { Surface(color = MaterialTheme.colors.background) { UI() } } }
    }

    @Composable
    private fun UI() {
        val scaffoldState = rememberScaffoldState() // You cannot reference directly composables in a non-compose location but you can refer their instances
        val coroutineScope = rememberCoroutineScope()

        val switchResult = remember { mutableStateOf("") }
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

                    ComposeBackPress()
                    ComposeAlertDialogs()
                    ComposeSnackbars(scaffoldState, coroutineScope)
                    ComposeButtons()
                    ComposeIconButtons()
                    ComposeTexts()
                    ComposeTextFields()
                    ComposeCards()
                    ComposeShapes()
                    ComposeImages()
                    ComposeAndroidViews()
                    ComposeRadioButtons()
                    ComposeMenus()
                    ComposeProgressBars()

                    Board(title = VERTICAL_LIST.value) {
                        // Shimmer Layout
                        // Vertical List View with Search
                        // Horizontal List View with Search
                        // Grid List View with Search

//                        val list = listOf("A", "B", "C", "D") + ((0..100).map { it.toString() })
//                        LazyColumn(modifier = Modifier.fillMaxHeight()) {
//                            items(items = list, itemContent = { item ->
//                                Log.d("COMPOSE", "This get rendered $item")
//                                when (item) {
//                                    "A" -> Text(text = item, style = TextStyle(fontSize = 80.sp))
//                                    "B" -> Button(onClick = {}) { Text(text = item, style = TextStyle(fontSize = 80.sp)) }
//                                    "C" -> Unit
//                                    "D" -> Text(text = item)
//                                    else -> Text(text = item, style = TextStyle(fontSize = 80.sp))
//                                }
//                            })
//                        }
                    }

                    Board(title = HORIZONTAL_LIST.value) {
//                        val list = listOf("A", "B", "C", "D") + ((0..100).map { it.toString() })
//                        LazyRow(modifier = Modifier.fillMaxHeight()) {
//                            items(items = list, itemContent = { item ->
//                                Log.d("COMPOSE", "This get rendered $item")
//                                when (item) {
//                                    "A" -> Text(text = item, style = TextStyle(fontSize = 80.sp))
//                                    "B" -> Button(onClick = {}) { Text(text = item, style = TextStyle(fontSize = 80.sp)) }
//                                    "C" -> Unit
//                                    "D" -> Text(text = item)
//                                    else -> Text(text = item, style = TextStyle(fontSize = 80.sp))
//                                }
//                            })
//                        }
                    }

                    Board(title = VERTICAL_GRID_LIST.value) {
//                        @ExperimentalFoundationApi
//                        @Composable
//                        fun GridList() {
//                            val list = (1..10).map { it.toString() }
//                            LazyVerticalGrid(
//                                cells = GridCells.Adaptive(128.dp),
//                                contentPadding = PaddingValues(start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp),
//                                content = {
//                                    items(list.size) { index ->
//                                        Card(backgroundColor = Color.Red, modifier = Modifier.padding(4.dp).fillMaxWidth(), elevation = 8.dp) {
//                                            Text(text = list[index], color = Color(0xFFFFFFFF), modifier = Modifier.padding(16.dp))
//                                        }
//                                    }
//                                }
//                            )
//                        }
                    }

                    Board(title = GESTURES.value) {
                        // Long Press
                        // Double Tap
                        // Tap
                        // Drag
                        FlowRow {

                        }
                    }

                    Board(title = ANIMATIONS.value) {
                        // Single Value Animation
                        // Crossfade animation
                        // Snap Animations
                        // Tween Animations
                        // Vector animations
                        // Animated Values
                        // Spring Animations

//                        val currentColor = remember { mutableStateOf(Color.Red) }
//                        Row {
//                            MyColors.values().forEach { myColor ->
//                                Button(
//                                    onClick = { currentColor.value = myColor.color },
//                                    modifier = Modifier
//                                        .weight(1f, true)
//                                        .height(48.dp)
//                                        .background(myColor.color), colors = ButtonDefaults.buttonColors(backgroundColor = myColor.color)
//                                ) { Text(myColor.name) }
//                            }
//                        }
//                        Crossfade(targetState = currentColor.value, animationSpec = tween(3000)) { selectedColor ->
//                            Box(modifier = Modifier.fillMaxSize().background(selectedColor.value, shape = RectangleShape))
//                        }
                    }

                    Board(title = SWITCH.value, result = switchResult.value) {
                        val checkedState = remember { mutableStateOf(true) }
                        Switch(
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = it
                                switchResult.value = if (it) "ON" else "OFF"
                            }
                        )
                    }

                    Board(title = CHECKBOX.value, result = checkBoxResult.value) {
                        // Tristate Checkbox
                        val checkedState = remember { mutableStateOf(true) }
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = it
                                checkBoxResult.value = if (it) "Checked" else "Not Checked"
                            }
                        )
                    }

                    Board(title = SLIDER.value) {
                        val sliderPosition = remember { mutableStateOf(0f) }
                        Text(text = sliderPosition.value.toString(), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
                        Slider(value = sliderPosition.value, onValueChange = { sliderPosition.value = it })
                    }

                    Board(title = FLOATING_ACTION_BUTTON.value) {
                        FlowRow {
                            FloatingActionButton(
                                modifier = Modifier.align(Alignment.End).padding(12.dp),
                                onClick = {}
                            ) { Text("+") }

                            FloatingActionButton(onClick = { /*do something*/ }) {
                                Text("FloatingActionButton")
                            }
                        }
                    }

                    Board(title = EXTENDED_FLOATING_ACTION_BUTTON.value) {
                        FlowRow {
                            ExtendedFloatingActionButton(
                                icon = { Icon(Icons.Filled.Favorite, "") },
                                text = { Text("FloatingActionButton") },
                                onClick = { /*do something*/ },
                                elevation = FloatingActionButtonDefaults.elevation(8.dp)
                            )
                        }
                    }

                    Board(title = CANVAS.value) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawRect(Color.Blue, topLeft = Offset(0f, 0f), size = Size(this.size.width, 55f))
                            drawCircle(Color.Red, center = Offset(50f, 200f), radius = 40f)
                            drawLine(color = Color.Green, start = Offset(20f, 0f), end = Offset(200f, 200f), strokeWidth = 5f)
                            drawArc(
                                color = Color.Black,
                                startAngle = 0f,
                                sweepAngle = 60f,
                                useCenter = true,
                                size = Size(300f, 300f),
                                topLeft = Offset(60f, 60f)
                            )
                        }
                    }

                    Board(title = BOTTOM_NAVIGATION_BAR.value) {
                        FlowRow {

                        }
                    }

                    Board(title = BOTTOM_APP_BAR.value) {
                        FlowRow {

                        }
                    }

                    Board(title = TOP_APP_BAR.value) {
                        TopAppBar(
                            elevation = 4.dp,
                            title = { Text("TopAppBar") },
                            backgroundColor = MaterialTheme.colors.primarySurface,
                            navigationIcon = {
                                IconButton(onClick = { /* Do Something */ }) {
                                    Icon(Icons.Filled.ArrowBack, null)
                                }
                            }, actions = {
                                IconButton(onClick = { /* Do Something */ }) {
                                    Icon(Icons.Filled.Share, null)
                                }
                                IconButton(onClick = { /* Do Something */ }) {
                                    Icon(Icons.Filled.Settings, null)
                                }
                            })
                    }

                    Board(title = BACKDROP_SCAFFOLD.value) {
                        FlowRow {

                        }
                    }

                    Board(title = TAB_ROW.value) {
                        FlowRow {

                        }
                    }

                    Board(title = TAB.value) {
                        FlowRow {

                        }
                    }

                    Board(title = SCROLLABLE_TAB_ROW.value) {
                        FlowRow {

                        }
                    }

                    Board(title = DIVIDER.value) {
                        FlowRow {

                        }
                    }

                    Board(title = FLOW_ROW.value) {
                        FlowRow {

                        }
                    }

                    Board(title = FLOW_COLUMN.value) {
                        FlowRow {

                        }
                    }

                    Board(title = SCROLLABLE_ROW.value) {
                        FlowRow {

                        }
                    }

                    Board(title = SCROLLABLE_COLUMN.value) {
                        FlowRow {

                        }
                    }

                    Board(title = SPACER.value) {
                        Text("Hello")
                        Spacer(modifier = Modifier.size(30.dp))
                        Text("World")
                    }

                    Board(title = SCAFFOLD_LAYOUT.value) {
//                        val materialBlue700 = Color(0xFF1976D2)
//                        val innerScaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
//                        Scaffold(
//                            scaffoldState = innerScaffoldState,
//                            topBar = { TopAppBar(title = { Text("TopAppBar") }, backgroundColor = materialBlue700) },
//                            floatingActionButtonPosition = FabPosition.End,
//                            floatingActionButton = { FloatingActionButton(onClick = {}) { Text("X") } },
//                            drawerContent = { Text(text = "drawerContent") },
//                            content = { Text("BodyContent") },
//                            bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } }
//                        )
                    }

                    Board(title = STACK_LAYOUT.value) {
                        FlowRow {

                        }
                    }

                    Board(title = CONSTRAINT_LAYOUT.value) {
                        ConstraintLayout(modifier = Modifier.size(200.dp)) {
                            val (redBox, blueBox, yellowBox, text) = createRefs()
                            Box(modifier = Modifier
                                .size(50.dp)
                                .background(Color.Red)
                                .constrainAs(redBox) {})
                            Box(modifier = Modifier
                                .size(50.dp)
                                .background(Color.Blue)
                                .constrainAs(blueBox) {
                                    top.linkTo(redBox.bottom)
                                    start.linkTo(redBox.end)
                                })
                            Box(modifier = Modifier
                                .size(50.dp)
                                .background(Color.Yellow)
                                .constrainAs(yellowBox) {
                                    bottom.linkTo(blueBox.bottom)
                                    start.linkTo(blueBox.end, 20.dp)
                                })
                            Text("Hello World", modifier = Modifier.constrainAs(text) {
                                top.linkTo(parent.top)
                                start.linkTo(yellowBox.start)
                            })
                        }
                    }

                    Board(title = BOX_LAYOUT.value) {
                        // Stack elements
                        Box(modifier = Modifier.fillMaxSize()) {
                            Card(backgroundColor = Color.Black, modifier = Modifier.padding(all = 16.dp)) { Text(text = "Black") }
                            32.dp.HorizontalSpace()
                            Card(backgroundColor = Color.Magenta, modifier = Modifier.padding(all = 16.dp)) { Text(text = "Megenta") }
                            64.dp.HorizontalSpace()
                            Card(backgroundColor = Color.Yellow, modifier = Modifier.padding(all = 16.dp)) { Text(text = "Yellow") }
                        }
                    }

                    Board(title = BOX_WITH_CONSTRAINTS_LAYOUT.value) {
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

                    Board(title = BADGE_BOX_LAYOUT.value) {
//                        @ExperimentalMaterialApi
//                        @Composable
//                        fun BadgeBox() {
//                            BottomNavigation {
//                                BottomNavigationItem(
//                                    icon = {
//                                        BadgeBox(badgeContent = { Text("8") }) {
//                                            Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
//                                        }
//                                    },
//                                    selected = false,
//                                    onClick = {}
//                                )
//                            }
//                        }
                    }

                    Board(title = MODAL_BOTTOM_SHEET.value) {
//                        @Composable
//                        @ExperimentalMaterialApi
//                        fun ModalBottomSheet() {
//                            val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
//                            val scope = rememberCoroutineScope()
//                            ModalBottomSheetLayout(sheetState = state, sheetContent = {
//                                LazyColumn {
//                                    items(50) {
//                                        ListItem(
//                                            text = { Text("Item $it") },
//                                            icon = { Icon(Icons.Default.Favorite, contentDescription = "Localized description") }
//                                        )
//                                    }
//                                }
//                            }) {
//                                Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
//                                    Text("Rest of the UI")
//                                    Spacer(Modifier.height(20.dp))
//                                    Button(onClick = { scope.launch { state.show() } }) { Text("Click to show sheet") }
//                                }
//                            }
//                        }
                    }

                    Board(title = MODAL_DRAWER.value) {
                        val drawerState = rememberDrawerState(DrawerValue.Closed)
                        val scope = rememberCoroutineScope()
                        ModalDrawer(
                            drawerState = drawerState,
                            drawerContent = {
                                Column {
                                    Text("Text in Drawer")
                                    Button(onClick = {
                                        scope.launch { drawerState.close() }
                                    }) { Text("Close Drawer") }
                                }
                            },
                            content = {
                                Column {
                                    Text("Text in Bodycontext")
                                    Button(onClick = {
                                        scope.launch { drawerState.open() }
                                    }) { Text("Click to open") }
                                }
                            }
                        )
                    }

                    Board(title = SURFACE.value) {
                        Surface(
                            modifier = Modifier.padding(8.dp),
                            border = BorderStroke(2.dp, Color.Red),
                            contentColor = Color.Blue,
                            elevation = 8.dp,
                            shape = CircleShape
                        ) {
                            Text("Hello World", modifier = Modifier.padding(8.dp))
                        }
                    }

                    Board(title = "Misc") {
                        @Composable
                        fun CompositionLocal() {
                            val localActiveUser = compositionLocalOf<String> { error("No user found!") }
                            CompositionLocalProvider(localActiveUser provides "User") {}
                        }

                        @Composable
                        fun LifecycleDemo() {
                            val count = remember { mutableStateOf(0) }
                            Column {
                                Button(onClick = { count.value++ }) { Text("Click me") }
                                if (count.value < 3) {
                                    SideEffect { Log.d("Compose", "onactive with value: " + count.value) }
                                    DisposableEffect(Unit) { onDispose { Log.d("Compose", "onDispose because value=" + count.value) } }
                                    Text(text = "You have clicked the button: " + count.value.toString())
                                }
                            }
                        }

                        @Composable
                        fun ComposeContext() {
                            MaterialTheme {
                                val context = LocalContext.current
                                Text(text = "Read this string from Context: " + context.getString(R.string.app_name))
                            }
                        }
                    }

                    24.dp.VerticalSpace()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() = ComposablesApp {}