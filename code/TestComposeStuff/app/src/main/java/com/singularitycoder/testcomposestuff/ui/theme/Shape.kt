package com.singularitycoder.testcomposestuff.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

// https://juliensalvi.medium.com/custom-shape-with-jetpack-compose-1cb48a991d42
// https://github.com/Oleur
@Composable
fun Ticket(modifier: Modifier) = Text(
    text = "🎉 Movie Ticket 🎉",
    style = TextStyle(
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
    ),
    textAlign = TextAlign.Center,
    modifier = modifier
        .wrapContentSize()
        .graphicsLayer {
            shadowElevation = 8.dp.toPx()
            shape = TicketShape(24.dp.toPx())
            clip = true
        }
        .background(color = ComposeColor.TitleColor)
        .drawBehind {
            scale(scale = 0.9f) {
                drawPath(
                    path = drawTicketPath(size = size, cornerRadius = 24.dp.toPx()),
                    color = ComposeColor.Teal200,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                    )
                )
            }
        }
        .padding(start = 32.dp, top = 64.dp, end = 32.dp, bottom = 64.dp)
)

fun drawTicketPath(size: Size, cornerRadius: Float): Path = Path().apply {
    reset()
    // Top left arc
    arcTo(
        rect = Rect(
            left = -cornerRadius,
            top = -cornerRadius,
            right = cornerRadius,
            bottom = cornerRadius
        ),
        startAngleDegrees = 90.0f,
        sweepAngleDegrees = -90.0f,
        forceMoveTo = false
    )
    lineTo(x = size.width - cornerRadius, y = 0f)
    // Top right arc
    arcTo(
        rect = Rect(
            left = size.width - cornerRadius,
            top = -cornerRadius,
            right = size.width + cornerRadius,
            bottom = cornerRadius
        ),
        startAngleDegrees = 180.0f,
        sweepAngleDegrees = -90.0f,
        forceMoveTo = false
    )
    lineTo(x = size.width, y = size.height - cornerRadius)
    // Bottom right arc
    arcTo(
        rect = Rect(
            left = size.width - cornerRadius,
            top = size.height - cornerRadius,
            right = size.width + cornerRadius,
            bottom = size.height + cornerRadius
        ),
        startAngleDegrees = 270.0f,
        sweepAngleDegrees = -90.0f,
        forceMoveTo = false
    )
    lineTo(x = cornerRadius, y = size.height)
    // Bottom left arc
    arcTo(
        rect = Rect(
            left = -cornerRadius,
            top = size.height - cornerRadius,
            right = cornerRadius,
            bottom = size.height + cornerRadius
        ),
        startAngleDegrees = 0.0f,
        sweepAngleDegrees = -90.0f,
        forceMoveTo = false
    )
    lineTo(x = 0f, y = cornerRadius)
    close()
}

class TicketShape(private val cornerRadius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline = Outline.Generic(path = drawTicketPath(size = size, cornerRadius = cornerRadius))   // Draw your custom path here
}