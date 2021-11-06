package com.singularitycoder.testcomposestuff.ui.utils

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.theme.ComposeColor
import java.util.*

@Composable
fun Search(
    searchString: MutableState<String>,
    backPress: () -> Unit
) = TextField(
    value = searchString.value,
    onValueChange = { value -> searchString.value = value },
    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp).focusRequester(FocusRequester()).focusable(true),
    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
    leadingIcon = {
        IconButton(onClick = { backPress.invoke() }) { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null) }
    },
    trailingIcon = {
        if (searchString.value != TextFieldValue("").text) {
            IconButton(onClick = {
                searchString.value = TextFieldValue("").text // Remove text from TextField when you press the 'X' icon
            }) { Icon(imageVector = Icons.Default.Cancel, contentDescription = null) }
        }
    },
    singleLine = true,
    label = { Text(text = "Search", color = ComposeColor.Purple100) },
    shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
    colors = TextFieldDefaults.textFieldColors(
        textColor = ComposeColor.White,
        cursorColor = ComposeColor.White,
        leadingIconColor = ComposeColor.White,
        trailingIconColor = ComposeColor.White,
        backgroundColor = ComposeColor.Purple500,
        focusedIndicatorColor = ComposeColor.Transparent,
        unfocusedIndicatorColor = ComposeColor.Transparent,
        disabledIndicatorColor = ComposeColor.Transparent
    )
)

@Composable
fun TopAppBarForList(
    title: String,
    hasActions: Boolean = false,
    isSearching: MutableState<Boolean>? = null,
    backPress: () -> Unit
) = TopAppBar(
    elevation = 4.dp,
    title = { Text(title) },
    backgroundColor = MaterialTheme.colors.primarySurface,
    actions = {
        if (hasActions) IconButton(onClick = { isSearching?.value = true }) { Icon(Icons.Filled.Search, null) }
    },
    navigationIcon = {
        IconButton(onClick = { backPress.invoke() }) { Icon(Icons.Filled.ArrowBack, null) }
    })

@Composable
fun SetStatusBarColor() {
    // https://stackoverflow.com/questions/65610216/how-to-change-statusbar-color-in-jetpack-compose
    val systemUiController = rememberSystemUiController()
    if (isSystemInDarkTheme()) systemUiController.setSystemBarsColor(color = ComposeColor.Transparent)
    else systemUiController.setSystemBarsColor(color = ComposeColor.Purple700)
}

@Composable
fun Board(
    title: String,
    result: String = "",
    titleBottomPadding: Boolean = true,
    shouldShow: Boolean = true,
    stuff: @Composable ColumnScope.() -> Unit
) {
    if (shouldShow) Card(
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
    border = BorderStroke(width = 1.dp, color = ComposeColor.Purple500),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = ComposeColor.Transparent,
        contentColor = ComposeColor.Purple500,
        disabledBackgroundColor = ComposeColor.Transparent,
        disabledContentColor = ComposeColor.Purple200
    ),
    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
    onClick = { action.invoke() }
) { Text(text = actionText) }

@Composable
fun Words(text: String) = Text(
    text = text,
    color = ComposeColor.TitleColor,
    fontWeight = FontWeight.Medium,
    modifier = Modifier.fillMaxWidth(),
    textDecoration = TextDecoration.None,
    style = MaterialTheme.typography.body1
)

@Composable
fun Result(result: String) = Text(
    text = result,
    color = ComposeColor.SubtitleColor,
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
                backgroundColor = ComposeColor.Transparent,
                contentColor = ComposeColor.Purple200,
                disabledBackgroundColor = ComposeColor.Transparent,
                disabledContentColor = ComposeColor.Transparent
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
        color = ComposeColor.LightBlack,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.fillMaxWidth()
    )

    @Composable
    fun AlertBtn(actionText: String, action: () -> Unit) = Button(
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Transparent, contentColor = ComposeColor.Purple500),
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

@Composable
fun VerticalListItem(item: Item) {
    val typography = MaterialTheme.typography
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Card(elevation = 0.dp, border = BorderStroke(0.5.dp, Color.LightGray), shape = RoundedCornerShape(16.dp)) {
            Image(
                painter = painterResource(item.imageId),
                modifier = Modifier.height(176.dp).fillMaxWidth().clip(RoundedCornerShape(16.dp)).clickable(onClick = {}),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        8.dp.VerticalSpace()
        Text(text = item.source.toUpCase(), style = typography.caption, color = Color.Gray)
        Text(text = item.title, style = typography.subtitle1, fontWeight = FontWeight.Bold)
        Text(text = item.subtitle, style = typography.body2)
    }
}

@Composable
fun CountryListItem(country: String, isFirstItem: Boolean = false) {
    Column(modifier = Modifier.clickable(onClick = {}).background(ComposeColor.White).fillMaxWidth()) {
        Text(
            text = country,
            fontSize = 16.sp,
            color = ComposeColor.TitleColor,
            modifier = Modifier.padding(all = 16.dp)
        )
        Divider(color = Color.LightGray, thickness = 0.5.dp, startIndent = 16.dp)
    }
}

@Composable
fun HorizontalAnimeListItem(item: Item, isLastItem: Boolean = false) {
    Column(modifier = Modifier.size(width = 144.dp, height = 228.dp).padding(start = 16.dp, end = if (isLastItem) 16.dp else 0.dp)) {
        Card(elevation = 0.dp, border = BorderStroke(0.5.dp, Color.LightGray)) {
            Image(
                painter = painterResource(id = item.imageId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.height(196.dp).fillMaxWidth().clickable(onClick = {})
            )
        }
        Column(modifier = Modifier.padding(top = 4.dp)) {
            Text(text = item.title, style = typography.subtitle2, fontWeight = FontWeight.Normal)
        }
    }
}

@Composable
fun GridAnimeListItem(item: Item, paddingStart: Dp, paddingEnd: Dp) {
    Column(modifier = Modifier.size(width = 144.dp, height = 228.dp).padding(start = paddingStart, end = paddingEnd)) {
        Card(elevation = 0.dp, border = BorderStroke(0.5.dp, Color.LightGray)) {
            Image(
                painter = painterResource(id = item.imageId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.height(196.dp).fillMaxWidth().clickable(onClick = {})
            )
        }
        Column(modifier = Modifier.padding(top = 4.dp)) {
            Text(text = item.title, style = typography.subtitle2, fontWeight = FontWeight.Normal)
        }
    }
}

@Composable
fun StoryListItem(item: Item, isLastItem: Boolean = false) {
    val imageModifier = Modifier.padding(start = 16.dp, end = if (isLastItem) 16.dp else 0.dp).height(60.dp).width(60.dp)
        .clip(CircleShape)
        .clickable(onClick = {})
        .border(
            shape = CircleShape,
            border = BorderStroke(
                width = 3.dp,
                brush = Brush.linearGradient(
                    colors = ComposeColor.GradientTealPurple200,
                    start = Offset(0f, 0f),
                    end = Offset(100f, 100f)
                )
            )
        )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = item.imageId),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = imageModifier
        )
        Text(text = item.title, style = typography.caption, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(start = 16.dp))
    }
}

@Composable
fun HorizontalNatureListItem(item: Item, isLastItem: Boolean = false) {
    Card(shape = MaterialTheme.shapes.medium, modifier = Modifier.size(280.dp, 200.dp).padding(start = 16.dp, end = if (isLastItem) 16.dp else 0.dp), elevation = 8.dp) {
        Column(modifier = Modifier.clickable(onClick = { })) {
            Image(
                painter = painterResource(id = item.imageId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(start = 12.dp, top = 8.dp, end = 12.dp, bottom = 8.dp)) {
                Text(text = item.source.toUpCase(), fontSize = 12.sp, color = Color.Gray, maxLines = 1)
                Text(text = item.title, style = typography.body2, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(text = item.subtitle, style = typography.body2, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeUtilsPreview() = ComposablesApp {
    HorizontalAnimeListItem(getAnimeItemList()[0])
}