package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.singularitycoder.testcomposestuff.ui.theme.AppColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.Board
import com.singularitycoder.testcomposestuff.ui.utils.Composables

@Composable
fun ComposeCards() {
    Board(title = Composables.CARD.value, titleBottomPadding = false) {
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
}

@Preview(showBackground = true)
@Composable
fun ComposeCardsPreview() = ComposablesApp { ComposeCards() }