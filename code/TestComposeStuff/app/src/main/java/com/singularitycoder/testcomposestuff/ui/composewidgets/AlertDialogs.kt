package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentTransaction
import com.google.accompanist.flowlayout.FlowRow
import com.singularitycoder.testcomposestuff.R
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.*

@Composable
fun ComposeAlertDialogs() {
    // FIXME Long text is hiding the buttons
    val CUSTOM_ALERT_DIALOG = "CUSTOM_ALERT_DIALOG"
    val alertResult = remember { mutableStateOf("") }
    val context = LocalContext.current
    Board(title = Composables.ALERT_DIALOG.value, result = alertResult.value, titleBottomPadding = false) {
        val showAlert = remember { mutableStateOf(false) }
        FlowRow {
            DefaultButton(actionText = "Simple Dialog") { showAlert.value = true }
            if (showAlert.value) {
                SimpleAlertDialog(
                    title = "Simple Alert Dialog",
                    message = context.getString(R.string.long_message),
                    positiveBtnText = "Ok",
                    negativeBtnText = "Cancel",
                    positiveBtnAction = {
                        alertResult.value = "You read it!"
                        showAlert.value = false
                    },
                    negativeBtnAction = {
                        alertResult.value = "You didn't read it!"
                        showAlert.value = false
                    },
                    dismissAction = {
                        alertResult.value = "You dismissed the alert!"
                        showAlert.value = false // If false then dialog cancels on touch of scrim
                    }
                )
            }
            DefaultButton(actionText = "Dialog Fragment") {
                val fragmentTransaction = context.getActivity()?.supportFragmentManager?.beginTransaction().apply {
                    this?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    val previousFragment = context.getActivity()?.supportFragmentManager?.findFragmentByTag(CUSTOM_ALERT_DIALOG)
                    if (null != previousFragment) this?.remove(previousFragment)
                    this?.addToBackStack(null)
                }
                fragmentTransaction ?: return@DefaultButton
                CustomDialogFragment(
                    title = "Custom Dialog Fragment",
                    message = context.getString(R.string.long_message),
                    imgContentDesc = context.getString(R.string.app_name),
                    positiveBtnText = "Ok",
                    negativeBtnText = "Cancel",
                    image = R.drawable.fight,
                    positiveBtnAction = { alertResult.value = "You read it!" },
                    negativeBtnAction = { alertResult.value = "You didn't read it!" }
                ).show(fragmentTransaction, CUSTOM_ALERT_DIALOG)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeAlertDialogsPreview() = ComposablesApp { ComposeAlertDialogs() }