package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.*

@Composable
fun ComposeRadioButtons() {
    val radioBtnResult = remember { mutableStateOf("") }
    Board(title = Composables.RADIO_BUTTON.value, result = radioBtnResult.value) {
        // Radio Group
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(bombList[1]) }
        fun onRadioBtnClick(bomb: String) {
            onOptionSelected(bomb)
            radioBtnResult.value = "$bomb selected!"
        }
        bombList.forEach { bomb: String ->
            8.dp.VerticalSpace()
            Row(modifier = Modifier.fillMaxWidth().selectable(selected = bomb == selectedOption, onClick = { onRadioBtnClick(bomb) })) {
                RadioButton(selected = bomb == selectedOption, onClick = { onRadioBtnClick(bomb) })
                16.dp.HorizontalSpace()
                Text(text = bomb)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeRadioButtonsPreview() = ComposablesApp { ComposeRadioButtons() }