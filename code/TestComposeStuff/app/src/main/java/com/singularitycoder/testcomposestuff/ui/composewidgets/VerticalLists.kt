package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentTransaction
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.utils.*
import com.singularitycoder.testcomposestuff.ui.verticallists.CountriesListDialogFragment
import com.singularitycoder.testcomposestuff.ui.verticallists.VerticalListDialogFragment

@Composable
fun ComposeVerticalLists() {
    Board(title = Composables.VERTICAL_LIST.value) {
        // Shimmer Layout
        // Vertical List View with Search
        // Horizontal List View with Search
        // Grid List View with Search
        // Use Navigation components

        val VERTICAL_LIST_FRAGMENT = "VERTICAL_LIST_FRAGMENT"
        val COUNTRIES_LIST_FRAGMENT = "COUNTRIES_LIST_FRAGMENT"
        val context = LocalContext.current

        DefaultButton(actionText = "Vertical List") {
            val fragmentTransaction = context.getActivity()?.supportFragmentManager?.beginTransaction().apply {
                this?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                val previousFragment = context.getActivity()?.supportFragmentManager?.findFragmentByTag(VERTICAL_LIST_FRAGMENT)
                if (null != previousFragment) this?.remove(previousFragment)
                this?.addToBackStack(null)
            }
            fragmentTransaction ?: return@DefaultButton
            VerticalListDialogFragment().show(fragmentTransaction, VERTICAL_LIST_FRAGMENT)
        }

        DefaultButton(actionText = "Countries List") {
            val fragmentTransaction = context.getActivity()?.supportFragmentManager?.beginTransaction().apply {
                this?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                val previousFragment = context.getActivity()?.supportFragmentManager?.findFragmentByTag(COUNTRIES_LIST_FRAGMENT)
                if (null != previousFragment) this?.remove(previousFragment)
                this?.addToBackStack(null)
            }
            fragmentTransaction ?: return@DefaultButton
            CountriesListDialogFragment().show(fragmentTransaction, COUNTRIES_LIST_FRAGMENT)
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun ComposeVerticalListsPreview() = ComposablesApp { ComposeVerticalLists() }