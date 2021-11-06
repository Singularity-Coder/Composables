package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentTransaction
import com.singularitycoder.testcomposestuff.ui.utils.*

@ExperimentalFoundationApi
@Composable
fun ComposeVerticalGridLists() {
    Board(title = Composables.VERTICAL_GRID_LIST.value) {
        val GRID_LIST_FRAGMENT = "GRID_LIST_FRAGMENT"
        val context = LocalContext.current
        DefaultButton(actionText = "Grid List") {
            val fragmentTransaction = context.getActivity()?.supportFragmentManager?.beginTransaction().apply {
                this?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                val previousFragment = context.getActivity()?.supportFragmentManager?.findFragmentByTag(GRID_LIST_FRAGMENT)
                if (null != previousFragment) this?.remove(previousFragment)
                this?.addToBackStack(null)
            }
            fragmentTransaction ?: return@DefaultButton
            GridListDialogFragment().show(fragmentTransaction, GRID_LIST_FRAGMENT)
        }
    }
}