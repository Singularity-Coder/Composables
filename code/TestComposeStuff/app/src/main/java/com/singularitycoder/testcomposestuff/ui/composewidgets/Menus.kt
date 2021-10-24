package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.singularitycoder.testcomposestuff.R
import com.singularitycoder.testcomposestuff.ui.theme.AppColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.*

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
                        Icon(painter = painterResource(id = menuIconList[index]), contentDescription = "Menu Icon", tint = AppColor.Purple500)
                        16.dp.HorizontalSpace()
                        Text(text = value, maxLines = 1)
                    }
                }
            }

            val expanded = remember { mutableStateOf(false) }
            val selectedIndex = remember { mutableStateOf(0) }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp).clickable(onClick = { expanded.value = true }),
                value = hobbyList[selectedIndex.value],
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Select Hobby", color = AppColor.Purple700) },
                trailingIcon = { Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_drop_down_24), contentDescription = null) }
            )

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.fillMaxWidth().background(AppColor.White)
            ) {
                hobbyList.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        selectedIndex.value = index
                        expanded.value = false
                    }) {
                        Text(text = s)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeMenusPreview() = ComposablesApp { ComposeMenus() }