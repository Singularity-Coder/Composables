package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singularitycoder.testcomposestuff.R
import com.singularitycoder.testcomposestuff.ui.theme.ComposeColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.Board
import com.singularitycoder.testcomposestuff.ui.utils.Composables
import com.singularitycoder.testcomposestuff.ui.utils.VerticalSpace

// Facbook TobAppBar

@Composable
fun ComposeTopAppBars() {
    val topAppBarResult = remember { mutableStateOf("") }
    Board(title = Composables.TOP_APP_BAR.value, result = topAppBarResult.value) {
        TopAppBar(title = { Text(text = "Home") }, elevation = 8.dp)

        8.dp.VerticalSpace()

        TopAppBar(
            elevation = 4.dp,
            title = { Text("TopAppBar") },
            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                IconButton(onClick = { topAppBarResult.value = "Back Button Pressed!" }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }, actions = {
                IconButton(onClick = { topAppBarResult.value = "Share Button Pressed!" }) {
                    Icon(Icons.Filled.Share, null)
                }
                IconButton(onClick = { topAppBarResult.value = "Settings Button Pressed!" }) {
                    Icon(Icons.Filled.Settings, null)
                }
            })

        8.dp.VerticalSpace()

        TopAppBar(
            title = { Text(text = "Instagram", fontFamily = FontFamily.Cursive, fontWeight = FontWeight.Black) },
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface,
            elevation = 8.dp,
            navigationIcon = {
                IconButton(onClick = { topAppBarResult.value = "Camera Button Pressed!" }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_photo_camera_24),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            },
            actions = {
                IconButton(onClick = { topAppBarResult.value = "Direct Messaging Button Pressed!" }) {
                    Icon(painter = painterResource(id = R.drawable.ic_outline_send_24), contentDescription = null, tint = Color.Black)
                }
            }
        )

        8.dp.VerticalSpace()

        TopAppBar(
            title = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_twitter),
                    contentDescription = null,
                    tint = ComposeColor.TwitterColor,
                    modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
                )
            },
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface,
            elevation = 8.dp,
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.pic1),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .clickable { topAppBarResult.value = "Profile Button Pressed!" }
                )
            },
            actions = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_outline_auto_awesome_24),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.padding(horizontal = 8.dp).clickable { topAppBarResult.value = "Highlights Button Pressed!" }
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeTopAppBarsPreview() = ComposablesApp { ComposeTopAppBars() }