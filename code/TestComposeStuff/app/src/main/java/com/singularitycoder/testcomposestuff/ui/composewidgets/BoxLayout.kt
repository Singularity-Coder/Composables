package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.theme.ComposeColor
import com.singularitycoder.testcomposestuff.ui.utils.Board
import com.singularitycoder.testcomposestuff.ui.utils.Composables

@Composable
fun ComposeBoxLayout() {
    Board(title = Composables.BOX_LAYOUT.value) {
        // Stack elements
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
            val boxModifier = Modifier.height(224.dp).fillMaxWidth()

            @Composable
            fun CardBox(backgroundColor: Color, modifier: Modifier) = Card(
                backgroundColor = backgroundColor,
                modifier = modifier,
                elevation = 10.dp,
                shape = RoundedCornerShape(12.dp)
            ) {}

            CardBox(backgroundColor = ComposeColor.MdCyan50, modifier = boxModifier)
            CardBox(backgroundColor = ComposeColor.MdCyan200, modifier = boxModifier.padding(start = 8.dp, top = 8.dp, bottom = 24.dp, end = 24.dp))
            CardBox(backgroundColor = ComposeColor.MdCyan400, modifier = boxModifier.padding(start = 16.dp, top = 16.dp, bottom = 48.dp, end = 48.dp))
            CardBox(backgroundColor = ComposeColor.MdCyan600, modifier = boxModifier.padding(start = 24.dp, top = 24.dp, bottom = 72.dp, end = 72.dp))
            CardBox(backgroundColor = ComposeColor.MdCyan800, modifier = boxModifier.padding(start = 32.dp, top = 32.dp, bottom = 96.dp, end = 96.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeBoxLayoutPreview() = ComposablesApp { ComposeBoxLayout() }