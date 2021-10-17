package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.theme.Ticket
import com.singularitycoder.testcomposestuff.ui.utils.Board
import com.singularitycoder.testcomposestuff.ui.utils.Composables
import com.singularitycoder.testcomposestuff.ui.utils.CustomShape

@Composable
fun ComposeShapes() {
    Board(title = Composables.SHAPES.value) {
        val triangleShape = GenericShape { size, direction ->
            /**
             * You can draw custom shapes. Use a GenericShape.
            Inside the GenericShape you can draw your custom shape.
            You have access to the **size**-object. This is size of the composable that the shape is applied to.
            You can get the height with **size.height.value** and the width with **size.width.value**


            1) Initially the painter will start at the top left of the parent composable(0x,0y).
            With **moveTo()** you can set the coordinates of the painter. Here the coordinates will be set to the half width of the parent layout
            and a 0y coordinate.

            2) This will draw a line from the painter coordinates, which were set in **1)**, to the bottom right corner of the parent layout.
            The painter coordinates are then automatically set to this corner.

            3) This will draw a line to the bottom left corner. GenericShape will implicit execute the **close()**-function. **close()** will draw a line from the last painter coordinates to the first definied.
             */

            moveTo(size.width / 2f, 0f)
            // This will draw a line from the cursor to the x/y coordinates
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
        }

        FlowRow {
            @Composable
            fun BoxShape(shape: Shape) {
                Column(modifier = Modifier.wrapContentSize(Alignment.Center).clip(shape).padding(end = 8.dp, bottom = 8.dp)) {
                    Box(modifier = Modifier.size(80.dp).background(Color.Blue)) {}
                }
            }

            BoxShape(shape = RoundedCornerShape(16.dp))
            BoxShape(shape = CutCornerShape(16.dp))
            BoxShape(shape = RectangleShape)
            BoxShape(shape = CustomShape())
            BoxShape(shape = triangleShape)

            Ticket(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeShapesPreview() = ComposablesApp { ComposeShapes() }