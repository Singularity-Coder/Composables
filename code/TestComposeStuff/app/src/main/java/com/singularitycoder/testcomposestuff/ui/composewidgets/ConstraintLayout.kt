package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.theme.ComposeColor
import com.singularitycoder.testcomposestuff.utils.Board
import com.singularitycoder.testcomposestuff.utils.Composables

@Composable
fun ComposeConstraintLayout() {
    Board(title = Composables.CONSTRAINT_LAYOUT.value) {
        ConstraintLayout(modifier = Modifier.height(192.dp).fillMaxWidth()) {
            val (color100Box, color200Box, color300Box, color400Box, color500Box, color600Box, color700Box, color800Box, color900Box) = createRefs()

            Box(modifier = Modifier.size(72.dp).clip(CircleShape).background(ComposeColor.MdCyan100).constrainAs(color100Box) {})
            Box(modifier = Modifier.size(72.dp).clip(CircleShape).background(ComposeColor.MdCyan300).constrainAs(color300Box) {
                end.linkTo(parent.end)
            })
            Box(modifier = Modifier.size(72.dp).clip(CircleShape).background(ComposeColor.MdCyan500).constrainAs(color500Box) {
                bottom.linkTo(parent.bottom)
            })
            Box(modifier = Modifier.size(72.dp).clip(CircleShape).background(ComposeColor.MdCyan700).constrainAs(color700Box) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            })

            Box(modifier = Modifier.size(96.dp).clip(CircleShape).background(ComposeColor.MdCyan900).constrainAs(color900Box) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })

            Box(modifier = Modifier.size(24.dp).clip(CircleShape).background(ComposeColor.MdCyan200).constrainAs(color200Box) {
                end.linkTo(color900Box.start, 12.dp)
                top.linkTo(color900Box.top)
                bottom.linkTo(color900Box.bottom)
            })
            Box(modifier = Modifier.size(24.dp).clip(CircleShape).background(ComposeColor.MdCyan400).constrainAs(color400Box) {
                bottom.linkTo(color900Box.top, 12.dp)
                start.linkTo(color900Box.start)
                end.linkTo(color900Box.end)
            })
            Box(modifier = Modifier.size(24.dp).clip(CircleShape).background(ComposeColor.MdCyan600).constrainAs(color600Box) {
                start.linkTo(color900Box.end, 12.dp)
                top.linkTo(color900Box.top)
                bottom.linkTo(color900Box.bottom)
            })
            Box(modifier = Modifier.size(24.dp).clip(CircleShape).background(ComposeColor.MdCyan800).constrainAs(color800Box) {
                top.linkTo(color900Box.bottom, 12.dp)
                start.linkTo(color900Box.start)
                end.linkTo(color900Box.end)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeConstraintLayoutPreview() = ComposablesApp { ComposeConstraintLayout() }