package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.singularitycoder.testcomposestuff.R
import com.singularitycoder.testcomposestuff.ui.theme.ComposeColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.Board
import com.singularitycoder.testcomposestuff.ui.utils.Composables
import com.singularitycoder.testcomposestuff.ui.utils.HorizontalSpace

@Composable
fun ComposeIconButtons() {
    val iconBtnResult = remember { mutableStateOf("") }
    Board(title = Composables.ICON_BUTTON.value, result = iconBtnResult.value) {
        FlowRow {
            IconButton(onClick = { iconBtnResult.value = "You poked the eye! Arrrrrrrrgh..." }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_visibility_24), "Basic Icon Button", tint = ComposeColor.Purple500)
            }
            8.dp.HorizontalSpace()
            IconButton(onClick = { iconBtnResult.value = "You sent something. I know what that is. Huhuhahahaha..." }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_send_24), "Basic Icon Button", tint = Color.Green)
            }
            8.dp.HorizontalSpace()
            IconButton(onClick = { iconBtnResult.value = "\"ALL DAY GAMING\" mode activated!" }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_videogame_asset_24), "Basic Icon Button", tint = Color.Blue)
            }
            8.dp.HorizontalSpace()
            IconButton(onClick = { iconBtnResult.value = "Gotta kill 'em all!" }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_catching_pokemon_24), "Basic Icon Button", tint = ComposeColor.Teal200)
            }
            8.dp.HorizontalSpace()
            IconButton(onClick = { iconBtnResult.value = "Starting World War 3! Please wait..." }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_coronavirus_24), "Basic Icon Button", tint = Color.Red)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeIconButtonsPreview() = ComposablesApp { ComposeIconButtons() }