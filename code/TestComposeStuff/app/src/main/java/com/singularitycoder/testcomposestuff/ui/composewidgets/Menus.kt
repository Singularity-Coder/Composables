package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.singularitycoder.testcomposestuff.ui.theme.AppColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.*

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
                        Icon(painter = painterResource(id = menuIconList[index]), "Menu Icon", tint = AppColor.Purple500)
                        16.dp.HorizontalSpace()
                        Text(text = value, maxLines = 1)
                    }
                }
            }

            var expanded by remember { mutableStateOf(false) }
            var selectedIndex by remember { mutableStateOf(0) }
            Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.TopStart).padding(top = 16.dp)) {
                Text(hobbyList[selectedIndex], modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded = true }).background(Color.LightGray).padding(16.dp))
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth().background(AppColor.White)
                ) {
                    hobbyList.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            selectedIndex = index
                            expanded = false
                        }) {
                            Text(text = s)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeMenusPreview() = ComposablesApp { ComposeMenus() }