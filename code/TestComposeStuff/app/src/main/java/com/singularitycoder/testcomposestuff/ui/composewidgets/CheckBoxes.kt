package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.theme.ComposeColor
import com.singularitycoder.testcomposestuff.utils.Board
import com.singularitycoder.testcomposestuff.utils.Composables
import com.singularitycoder.testcomposestuff.utils.HorizontalSpace

@Composable
fun ComposeCheckBoxes() {
    val checkBoxResult = remember { mutableStateOf("") }
    Board(title = Composables.CHECKBOX.value, result = checkBoxResult.value) {
        // Tristate Checkbox

        @Composable
        fun CheckSquare(checkBoxValue: MutableState<Boolean>, checkBoxText: String) {
            val checkBoxColors = CheckboxDefaults.colors(
                checkedColor = ComposeColor.Purple500,
                uncheckedColor = Color.Gray,
                checkmarkColor = ComposeColor.White,
                disabledColor = Color.LightGray,
                disabledIndeterminateColor = Color.LightGray
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkBoxValue.value,
                    colors = checkBoxColors,
                    onCheckedChange = { isChecked: Boolean ->
                        checkBoxValue.value = isChecked
                        checkBoxResult.value = if (isChecked) "$checkBoxText Checked" else "$checkBoxText Unchecked"
                    }
                )
                8.dp.HorizontalSpace()
                Text(
                    text = checkBoxText,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        checkBoxValue.value = !checkBoxValue.value
                        checkBoxResult.value = if (checkBoxValue.value) "$checkBoxText Checked" else "$checkBoxText Unchecked"
                    }
                )
            }
        }


        val checkedStateKnife = remember { mutableStateOf(true) }
        CheckSquare(checkBoxValue = checkedStateKnife, checkBoxText = "Flip Knife")

        val checkedStateGrenade = remember { mutableStateOf(false) }
        CheckSquare(checkBoxValue = checkedStateGrenade, checkBoxText = "HE Grenade")

        val checkedStateKreig = remember { mutableStateOf(false) }
        CheckSquare(checkBoxValue = checkedStateKreig, checkBoxText = "Kreig 550 Commando")

        val checkedStateAk47 = remember { mutableStateOf(false) }
        CheckSquare(checkBoxValue = checkedStateAk47, checkBoxText = "AK-47")

        val checkedStateNightHawk = remember { mutableStateOf(false) }
        CheckSquare(checkBoxValue = checkedStateNightHawk, checkBoxText = "Night Hawk")

        val checkedStateDesertEagle = remember { mutableStateOf(true) }
        CheckSquare(checkBoxValue = checkedStateDesertEagle, checkBoxText = "Desert Eagle")

        val checkedStateMaverik = remember { mutableStateOf(false) }
        CheckSquare(checkBoxValue = checkedStateMaverik, checkBoxText = "M4A1 Maverik")

        val checkedStateSniper = remember { mutableStateOf(true) }
        CheckSquare(checkBoxValue = checkedStateSniper, checkBoxText = "AWP Sniper")
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeCheckBoxesPreview() = ComposablesApp { ComposeCheckBoxes() }