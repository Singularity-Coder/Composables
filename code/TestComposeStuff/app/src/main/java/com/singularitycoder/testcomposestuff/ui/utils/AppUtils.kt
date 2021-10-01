package com.singularitycoder.testcomposestuff.ui.utils

import android.widget.Toast
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentTransaction
import com.singularitycoder.testcomposestuff.MainActivity
import com.singularitycoder.testcomposestuff.R

@Stable
fun MyColor(color: String): Color = Color("0xFF$color".toLong())

fun showDialogFragment(activity: MainActivity) {
    val fragmentTransaction = activity.supportFragmentManager.beginTransaction().apply {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        val previousFragment = activity.supportFragmentManager.findFragmentByTag("EN_ALERT")
        if (null != previousFragment) remove(previousFragment)
        addToBackStack(null)
    }
    CustomDialogFragment(
        image = R.drawable.ic_launcher_background,
        message = "Hell World Hell World Hell World Hell World Hell World Hell World Hell World Hell World Hell World Hell World Hell World",
        positiveBtnText = "OK",
        imgContentDesc = activity.getString(R.string.app_name),
        positiveBtnAction = {
            Toast.makeText(activity, "Hello Wolrd", Toast.LENGTH_LONG).show()
        }
    ).show(fragmentTransaction, "EN_ALERT")
}