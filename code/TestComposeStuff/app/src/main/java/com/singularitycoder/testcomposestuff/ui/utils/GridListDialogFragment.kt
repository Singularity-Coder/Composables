package com.singularitycoder.testcomposestuff.ui.utils

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.DialogFragment
import com.singularitycoder.testcomposestuff.ui.theme.AndroidColor

@ExperimentalFoundationApi
class GridListDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_NoTitleBar)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.parseColor(AndroidColor.purple700)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        Column {
                            TopAppBarForList("Grid List", backPress = { this@GridListDialogFragment.dismiss() })
                            GridItemList()
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun GridItemList() {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
        content = {
            items(getAnimeItemList().size) { index: Int ->
                when {
                    index == 0 -> GridAnimeListItem(item = getAnimeItemList()[index], paddingStart = 0.dp, paddingEnd = 8.dp)
                    index % 2 == 0 -> GridAnimeListItem(item = getAnimeItemList()[index], paddingStart = 0.dp, paddingEnd = 8.dp)
                    else -> GridAnimeListItem(item = getAnimeItemList()[index], paddingStart = 8.dp, paddingEnd = 0.dp)
                }
            }
        }
    )
}