package com.singularitycoder.testcomposestuff.ui.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.DialogFragment

class HorizontalListDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        HorizontalItemList()
                    }
                }
            }
        }
    }
}

// Stories like instagram
// Anime Posters
// App store wide posters
// Scroll 2 sets at once like apps or music

@Composable
fun HorizontalItemList() = Column {
    TopAppBar(
        elevation = 4.dp,
        title = { Text("Horizontal List") },
        backgroundColor = MaterialTheme.colors.primarySurface,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        })
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(modifier = Modifier.padding(16.dp), text = "Stories", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
        Stories()
        Text(modifier = Modifier.padding(16.dp), text = "Great Anime", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
        Anime()
        Text(modifier = Modifier.padding(16.dp), text = "Beautiful Nature", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
        Nature()
        32.dp.VerticalSpace()
    }
}

@Composable
fun Nature() {
    LazyRow {
        items(items = getNatureItemList()) { it: Item -> HorizontalListItem2(item = it, isLastItem = it.id == getNatureItemList().size) }
    }
}

@Composable
fun Anime() {
    LazyRow {
        items(items = getAnimeItemList()) { it: Item -> HorizontalListItem(item = it, isLastItem = it.id == getAnimeItemList().size) }
    }
}

@Composable
fun Stories() {
    LazyRow {
        items(getAnimeItemList()) { it: Item -> StoryListItem(item = it, isLastItem = it.id == getAnimeItemList().size) }
    }
}