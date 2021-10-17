package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singularitycoder.testcomposestuff.R
import com.singularitycoder.testcomposestuff.ui.theme.AppColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.Board
import com.singularitycoder.testcomposestuff.ui.utils.Composables
import com.singularitycoder.testcomposestuff.ui.utils.HorizontalSpace
import com.singularitycoder.testcomposestuff.ui.utils.VerticalSpace

@Composable
fun ComposeProgressBars() {
    Board(title = Composables.PROGRESS_BARS.value) {
        // Switching btw determiate to indetreminate and vice versa
        // Setting custom colors
        // Progress styles
        val progress = remember { mutableStateOf(0.5f) }
        val animatedProgress = animateFloatAsState(targetValue = progress.value, animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec).value

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
}

@Preview(showBackground = true)
@Composable
fun ComposeProgressBarsPreview() = ComposablesApp { ComposeProgressBars() }