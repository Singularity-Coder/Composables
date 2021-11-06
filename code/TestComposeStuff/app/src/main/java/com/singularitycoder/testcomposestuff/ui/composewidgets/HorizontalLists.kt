package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentTransaction
import com.singularitycoder.testcomposestuff.ui.utils.*

@Composable
fun ComposeHorizontalLists() {
    Board(title = Composables.HORIZONTAL_LIST.value) {
        val HOROIZONTAL_LIST_FRAGMENT = "HORZONTAL_LIST_FRAGMENT"
        val context = LocalContext.current
        DefaultButton(actionText = "Horizontal List") {
            val fragmentTransaction = context.getActivity()?.supportFragmentManager?.beginTransaction().apply {
                this?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                val previousFragment = context.getActivity()?.supportFragmentManager?.findFragmentByTag(HOROIZONTAL_LIST_FRAGMENT)
                if (null != previousFragment) this?.remove(previousFragment)
                this?.addToBackStack(null)
            }
            fragmentTransaction ?: return@DefaultButton
            HorizontalListDialogFragment().show(fragmentTransaction, HOROIZONTAL_LIST_FRAGMENT)
        }
    }
}