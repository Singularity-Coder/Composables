package com.singularitycoder.testcomposestuff.ui.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.DialogFragment

class VerticalListDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        VerticalItemList()
                    }
                }
            }
        }
    }
}

@Composable
fun VerticalItemList() {
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = { Text("Vertical List") },
            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            })
        LazyColumn {
            items(items = getNatureItemList(), itemContent = { item ->
                VerticalListItem(item = item)
            })
        }
    }
}