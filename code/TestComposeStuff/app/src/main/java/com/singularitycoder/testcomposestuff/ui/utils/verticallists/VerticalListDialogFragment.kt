package com.singularitycoder.testcomposestuff.ui.utils.verticallists

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.DialogFragment
import com.singularitycoder.testcomposestuff.ui.theme.AndroidColor
import com.singularitycoder.testcomposestuff.ui.utils.TopAppBarForList
import com.singularitycoder.testcomposestuff.ui.utils.VerticalListItem
import com.singularitycoder.testcomposestuff.ui.utils.getNatureItemList

class VerticalListDialogFragment : DialogFragment() {

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
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        Column {
                            TopAppBarForList("Vertical List", backPress = { this@VerticalListDialogFragment.dismiss() })
                            VerticalItemList()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VerticalItemList() {
    LazyColumn {
        items(items = getNatureItemList(), itemContent = { item ->
            VerticalListItem(item = item)
        })
    }
}