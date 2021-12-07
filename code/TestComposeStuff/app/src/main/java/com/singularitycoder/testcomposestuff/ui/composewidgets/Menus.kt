package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.google.accompanist.flowlayout.FlowRow
import com.singularitycoder.testcomposestuff.ui.theme.ComposeColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.utils.*

// Not working for some reason

@Composable
fun ComposeMenus() {
    val menuResult = remember { mutableStateOf("") }
    Board(title = Composables.MENU.value, result = menuResult.value) {
        FlowRow {
            val showSimpleMenu = remember { mutableStateOf(false) }
            val showMenuWithImage = remember { mutableStateOf(false) }

            DefaultButton(actionText = "Simple Menu") { showSimpleMenu.value = true }
            DropdownMenu(expanded = showSimpleMenu.value, onDismissRequest = { showSimpleMenu.value = false }) {
                hobbyList.forEachIndexed { index, value ->
                    DropdownMenuItem(onClick = {
                        showSimpleMenu.value = false
                        menuResult.value = "You clicked on ${hobbyList[index]}!"
                    }) { Text(text = value) }
                }
            }

            DefaultButton(actionText = "Menu With Icons") { showMenuWithImage.value = true }
            DropdownMenu(expanded = showMenuWithImage.value, modifier = Modifier.fillMaxWidth(), onDismissRequest = { showMenuWithImage.value = false }) {
                menuList.forEachIndexed { index, value ->
                    DropdownMenuItem(onClick = {
                        showMenuWithImage.value = false
                        menuResult.value = "You clicked on ${menuList[index]}!"
                    }) {
                        Icon(painter = painterResource(id = menuIconList[index]), contentDescription = "Menu Icon", tint = ComposeColor.Purple500)
                        16.dp.HorizontalSpace()
                        Text(text = value, maxLines = 1)
                    }
                }
            }

            // https://stackoverflow.com/questions/67111020/exposed-drop-down-menu-for-jetpack-compose
            Column {
                val expanded = remember { mutableStateOf(false) }
                val selectedIndex = remember { mutableStateOf(0) }
                val textFieldSize = remember { mutableStateOf(Size.Zero) }
                val icon = if (expanded.value) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable(onClick = { expanded.value = true })
                        .onGloballyPositioned { coordinates ->
                            textFieldSize.value = coordinates.size.toSize() // This value is used to assign to the DropDown the same width
                        },
                    value = hobbyList[selectedIndex.value],
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(text = "Select Hobby", color = ComposeColor.Purple700) },
                    trailingIcon = { Icon(imageVector = icon, contentDescription = null, Modifier.clickable { expanded.value = !expanded.value }) }
                )
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                    modifier = Modifier.fillMaxWidth().background(ComposeColor.White).width(with(LocalDensity.current) { textFieldSize.value.width.toDp() })
                ) {
                    hobbyList.forEachIndexed { index, value ->
                        DropdownMenuItem(onClick = {
                            selectedIndex.value = index
                            expanded.value = false
                        }) { Text(text = value) }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeMenusPreview() = ComposablesApp { ComposeMenus() }