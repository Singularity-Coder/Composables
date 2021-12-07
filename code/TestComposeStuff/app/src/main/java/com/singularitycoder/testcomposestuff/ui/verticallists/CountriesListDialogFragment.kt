package com.singularitycoder.testcomposestuff.ui.verticallists

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.DialogFragment
import com.singularitycoder.testcomposestuff.ui.theme.AndroidColor
import com.singularitycoder.testcomposestuff.utils.*

class CountriesListDialogFragment : DialogFragment() {

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
                            val searchString = remember { mutableStateOf("") }
                            val isSearching = remember { mutableStateOf(false) }
                            if (!isSearching.value) TopAppBarForList(
                                title = "Countries List",
                                hasActions = true,
                                isSearching = isSearching,
                                backPress = { this@CountriesListDialogFragment.dismiss() }
                            )
                            if (isSearching.value) Search(searchString = searchString, backPress = { this@CountriesListDialogFragment.dismiss() })
                            CountriesItemList(searchString = searchString)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CountriesItemList(searchString: MutableState<String>) {
    println("My srarch string: " + searchString.value)
    LazyColumn {
        items(
            items = if (searchString.value.isBlank()) getCountriesList() else getCountriesList().filter { it.contains(searchString.value, true) },
            itemContent = { item ->
                CountryListItem(country = item, isFirstItem = item == getCountriesList().first())
            }
        )
    }
}