package com.singularitycoder.testcomposestuff.ui.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.DialogFragment
import com.singularitycoder.testcomposestuff.ui.theme.AppColor
import com.singularitycoder.testcomposestuff.ui.utils.VerticalSpace

class CustomDialogFragment(
    val title: String = "NA",
    val message: String = "NA",
    val imgContentDesc: String = "NA",
    val positiveBtnText: String,
    val negativeBtnText: String = "NA",
    @DrawableRes val image: Int = android.R.drawable.ic_delete,
    val positiveBtnAction: () -> Unit = {},
    val negativeBtnAction: () -> Unit = {}
) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Surface(modifier = Modifier.padding(all = 24.dp), color = MaterialTheme.colors.background) {
                        CustomAlertDialog()
                    }
                }
            }
        }
    }

    @Composable
    fun CustomAlertDialog() {
        @Composable
        fun Words(
            text: String,
            fontSize: TextUnit,
            fontWeight: FontWeight?
        ) = Text(
            text = text,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = AppColor.LightBlack,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        @Composable
        fun Action(
            actionText: String,
            action: () -> Unit
        ) = Button(
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColor.Transparent, contentColor = AppColor.Purple500),
            onClick = action
        ) { Text(actionText) }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            if (image != android.R.drawable.ic_delete) Image(
                painter = painterResource(id = image),
                contentDescription = imgContentDesc,
                modifier = Modifier.size(160.dp),
                contentScale = ContentScale.Crop
            )
            if (image != android.R.drawable.ic_delete) VerticalSpace(spacing = 24.dp)
            if ("NA" != title) Words(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            if ("NA" != title) VerticalSpace(spacing = 12.dp)
            if ("NA" != message) Words(text = message, fontSize = 16.sp, fontWeight = FontWeight.Normal)
            if ("NA" != message) VerticalSpace(spacing = 24.dp)
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Action(actionText = positiveBtnText) {
                    this@CustomDialogFragment.dismiss()
                    positiveBtnAction.invoke()
                }
                Action(actionText = negativeBtnText) {
                    this@CustomDialogFragment.dismiss()
                    negativeBtnAction.invoke()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomAlertDialogPreview() = MaterialTheme {}