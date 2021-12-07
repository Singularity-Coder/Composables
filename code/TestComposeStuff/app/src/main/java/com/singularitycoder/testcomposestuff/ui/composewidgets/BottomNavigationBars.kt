package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.utils.Board
import com.singularitycoder.testcomposestuff.utils.Composables
import com.singularitycoder.testcomposestuff.utils.VerticalSpace

// On selection make them filled icons
// On selection make the icons bigger amd text is visible on selection

@Composable
fun ComposeBottomNavigationBars() {
    val bottomNavigationBarResult = remember { mutableStateOf("") }
    Board(title = Composables.BOTTOM_NAVIGATION_BAR.value, result = bottomNavigationBarResult.value) {
        val bottomNavEmailAppBarState = remember { mutableStateOf(EmailApp.HOME) }
        BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = null) },
                selected = bottomNavEmailAppBarState.value == EmailApp.HOME,
                label = { Text(text = "Home") },
                onClick = {
                    bottomNavEmailAppBarState.value = EmailApp.HOME
                    bottomNavigationBarResult.value = "Home Tab selected!"
                }
            )
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
                selected = bottomNavEmailAppBarState.value == EmailApp.SEARCH,
                label = { Text(text = "Search") },
                onClick = {
                    bottomNavEmailAppBarState.value = EmailApp.SEARCH
                    bottomNavigationBarResult.value = "Search Tab selected!"
                }
            )
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Outlined.Email, contentDescription = null) },
                selected = bottomNavEmailAppBarState.value == EmailApp.INBOX,
                label = { Text(text = "Inbox") },
                onClick = {
                    bottomNavEmailAppBarState.value = EmailApp.INBOX
                    bottomNavigationBarResult.value = "Inbox Tab selected!"
                }
            )
        }

        8.dp.VerticalSpace()

        val bottomNavContactsBarState = remember { mutableStateOf(ContactsApp.CALLS) }
        BottomNavigation {
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Outlined.Call, contentDescription = null) },
                selected = bottomNavContactsBarState.value == ContactsApp.CALLS,
                onClick = {
                    bottomNavContactsBarState.value = ContactsApp.CALLS
                    bottomNavigationBarResult.value = "Calls Tab selected!"
                },
            )
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null) },
                selected = bottomNavContactsBarState.value == ContactsApp.FAVORITES,
                onClick = {
                    bottomNavContactsBarState.value = ContactsApp.FAVORITES
                    bottomNavigationBarResult.value = "Favorites Tab selected!"
                }
            )
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Outlined.Add, contentDescription = null) },
                selected = bottomNavContactsBarState.value == ContactsApp.ADD,
                onClick = {
                    bottomNavContactsBarState.value = ContactsApp.ADD
                    bottomNavigationBarResult.value = "Add Contact Tab selected!"
                }
            )
            BottomNavigationItem(
                icon = { Icon(imageVector = Icons.Outlined.Settings, contentDescription = null) },
                selected = bottomNavContactsBarState.value == ContactsApp.SETTINGS,
                onClick = {
                    bottomNavContactsBarState.value = ContactsApp.SETTINGS
                    bottomNavigationBarResult.value = "Settings Tab selected!"
                }
            )
        }
    }
}

enum class EmailApp {
    HOME, SEARCH, INBOX
}

enum class ContactsApp {
    CALLS, FAVORITES, ADD, SETTINGS
}

@Preview(showBackground = true)
@Composable
fun ComposeBottomNavigationBarsPreview() = ComposablesApp { ComposeBottomNavigationBars() }