package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.singularitycoder.testcomposestuff.R
import com.singularitycoder.testcomposestuff.ui.theme.ComposeColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.utils.Board
import com.singularitycoder.testcomposestuff.utils.Composables
import com.singularitycoder.testcomposestuff.utils.Feedback
import com.singularitycoder.testcomposestuff.utils.VerticalSpace

@Composable
fun ComposeTextFields() {
    val textFieldResult = remember { mutableStateOf("") }
    Board(title = Composables.TEXT_FIELDS.value, result = textFieldResult.value) {
        // Long Description field
        // Default close icon func
        // IME options, Keyboard Type, Input State, Visual Transformation, Password Field, Handle value changes, Default value, text field value
        val basicTextFieldText = remember { mutableStateOf("Basic Text Field") }
        val textFieldText = remember { mutableStateOf("") }
        val outlinedTextFieldText = remember { mutableStateOf("") }
        val customTextFieldText = remember { mutableStateOf("") }
        val showScopedSnackbar = remember { mutableStateOf(false) }

        BasicTextField(
            modifier = Modifier.background(color = Color.LightGray).padding(all = 16.dp).fillMaxWidth(),
            value = basicTextFieldText.value,
            onValueChange = {
                textFieldResult.value = it
                basicTextFieldText.value = it
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            enabled = false,
            label = { Text("Read Only Text Field") },
            colors = TextFieldDefaults.textFieldColors(disabledIndicatorColor = Color.LightGray)
        )

        8.dp.VerticalSpace()

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textFieldText.value,
            onValueChange = { it: String ->
                textFieldResult.value = it
                textFieldText.value = it
            },
            label = { Text("Enter Name") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = outlinedTextFieldText.value,
            onValueChange = { it: String ->
                textFieldResult.value = it
                outlinedTextFieldText.value = it
            },
            label = { Text("Enter Name") }
        )

        8.dp.VerticalSpace()

        val passwordVisibility = remember { mutableStateOf(false) }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = customTextFieldText.value,
            onValueChange = { it: String ->
                textFieldResult.value = it
                customTextFieldText.value = it
            },
            textStyle = TextStyle(fontSize = 16.sp, fontFamily = FontFamily.SansSerif),
            enabled = true,
            isError = false,
            singleLine = true,
            maxLines = 1,
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(autoCorrect = false, keyboardType = KeyboardType.Password),
            label = { Text(text = "Enter Password", color = if (customTextFieldText.value.length > 10) ComposeColor.ErrorDark else ComposeColor.Purple700) },
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.ic_outline_lock_24), contentDescription = "Password Placeholder Image")
                }
            },
            trailingIcon = {
                if (customTextFieldText.value.length > 10) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_error_24),
                        contentDescription = "Warning",
                        tint = ComposeColor.ErrorDark,
                        modifier = Modifier.clickable { showScopedSnackbar.value = true }
                    )
                } else {
                    val image = if (passwordVisibility.value) {
                        painterResource(id = R.drawable.ic_baseline_visibility_off_24)
                    } else painterResource(id = R.drawable.ic_baseline_visibility_24)
                    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                        Icon(painter = image, "Trailing Icon", tint = ComposeColor.Purple500)
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = ComposeColor.TitleColor,
                backgroundColor = if (customTextFieldText.value.length > 10) ComposeColor.ErrorLight else ComposeColor.Purple100,
                focusedIndicatorColor = if (customTextFieldText.value.length > 10) ComposeColor.ErrorDark else ComposeColor.Purple700,
                unfocusedIndicatorColor = ComposeColor.Purple700,
                disabledIndicatorColor = ComposeColor.Purple200,
                errorIndicatorColor = ComposeColor.ErrorDark
            )
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            @Composable
            fun Words(text: String, modifier: Modifier = Modifier.fillMaxWidth(), textAlign: TextAlign = TextAlign.End) {
                Text(
                    text = text,
                    fontSize = 12.sp,
                    color = if (customTextFieldText.value.length > 10) ComposeColor.ErrorDark else Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = modifier,
                    textAlign = textAlign
                )
            }
            if (customTextFieldText.value.length > 10) Words(text = "Exceeded character limit!", modifier = Modifier.wrapContentWidth(), textAlign = TextAlign.Start)
            Words(text = "${customTextFieldText.value.length}/10")
        }
        if (showScopedSnackbar.value) Feedback("Password cannot be more than 10 characters!", btnText = "Ok") { showScopedSnackbar.value = false }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeTextFieldsPreview() = ComposablesApp { ComposeTextFields() }