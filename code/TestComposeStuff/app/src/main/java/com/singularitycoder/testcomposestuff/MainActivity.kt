package com.singularitycoder.testcomposestuff

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.singularitycoder.testcomposestuff.ui.theme.AppColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposablesApp { Surface(color = MaterialTheme.colors.background) { UI() } } }
    }

    @Composable
    private fun UI() {
        SetStatusBarColor()
        val openDialog = remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppColor.Purple700)
        ) {

            Text(
                text = "Composables",
                color = AppColor.White,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 0.dp),
                textDecoration = TextDecoration.None,
                style = MaterialTheme.typography.h4
            )

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(start = 24.dp, top = 0.dp, end = 24.dp, bottom = 0.dp)
            ) {

                Paper {
                    Words(text = "Alert Dialog")
                    FlowRow {
                        DefaultButton(actionText = "Simple Dialog") {
                            openDialog.value = true
                        }
                        if (openDialog.value) {
                            SimpleAlertDialog(
                                isCancellable = false,
                                showDialog = openDialog,
                                title = "Hello World",
                                message = "Helo WorldHelo WorldHelo WorldHelo WorldHelo WorldHelo WorldHelo WorldHelo WorldHelo WorldHelo WorldHelo WorldHelo World",
                                positiveBtnText = "OK",
                                negativeBtnText = "CANCEL",
                                positiveBtnAction = {},
                                negativeBtnAction = {},
                                dismissAction = Toast.makeText(this@MainActivity, "Hey hey hey", Toast.LENGTH_LONG)::show
                            )
                        }
                        DefaultButton(actionText = "Dialog Fragment") {
                            showDialogFragment(this@MainActivity)
                        }
                        DefaultButton(actionText = "Dialog 1") {}
                        DefaultButton(actionText = "Dialog 2") {}
                        DefaultButton(actionText = "Dialog 3") {}
                    }
                }

                Paper {
                    Words(text = "Snackbar")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Button")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Text")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Card")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Floating Action Button")
                    FlowRow {
                    }
                }

                Paper {
                    Words(text = "Extended Floating Action Button")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Scaffold")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Bottom Navigation Bar")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Top App Bar")
                    FlowRow {

                    }
                }
                Paper {
                    Words(text = "Bottom Navigation Item")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Backdrop Scaffold")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Bottom App Bar")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Progress")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Tab Row")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Tab")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Scrollable Tab Row")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Divider")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Switch")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Checkbox")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Radio Button")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Slider")
                    FlowRow {

                    }
                }

                Paper {
                    Words(text = "Icon Button")
                    FlowRow {

                    }
                }
                
                VerticalSpace(spacing = 24.dp)

            }
        }
    }

    @Composable
    private fun SetStatusBarColor() {
        val systemUiController = rememberSystemUiController()
        if (isSystemInDarkTheme()) {
            systemUiController.setSystemBarsColor(color = AppColor.Transparent)
        } else {
            systemUiController.setSystemBarsColor(color = AppColor.Purple700)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() = ComposablesApp {}