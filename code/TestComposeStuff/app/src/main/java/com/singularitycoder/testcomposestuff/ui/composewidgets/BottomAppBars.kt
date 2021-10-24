package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.Board
import com.singularitycoder.testcomposestuff.ui.utils.Composables

// Fab cut out AppBar

@Composable
fun ComposeBottomAppBars() {
    val bottomAppBarResult = remember { mutableStateOf("") }
    Board(title = Composables.BOTTOM_APP_BAR.value, result = bottomAppBarResult.value) {
        BottomAppBar(cutoutShape = CircleShape) {
            IconButton(onClick = { bottomAppBarResult.value = "More Button Clicked!" }) {
                Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
            }
            Text(text = "Bottom App Bar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeBottomAppBarsPreview() = ComposablesApp { ComposeBottomAppBars() }