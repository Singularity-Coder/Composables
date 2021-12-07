package com.singularitycoder.testcomposestuff.ui.composewidgets

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singularitycoder.testcomposestuff.R
import com.singularitycoder.testcomposestuff.ui.theme.ComposeColor
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.utils.*

@Composable
fun ComposePermissions() {
    val permissionsResult = remember { mutableStateOf("") }
    Board(title = Composables.PERMISSIONS.value, result = permissionsResult.value) {
        val permissionsArray = arrayOf(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
        )
        val showRationaleAlert = remember { mutableStateOf(false) }
        val composeSinglePermissionResult = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) permissionsResult.value = "Camera Permission Granted" else showRationaleAlert.value = true
        }
        val composeMultiplePermissionResult = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions: Map<String, @JvmSuppressWildcards Boolean> ->
            var grantedPermissionsCount = 0
            permissions.entries.forEach { it: Map.Entry<String, Boolean> ->
                Log.i("LOG", "${it.key} = ${it.value}")
                if (it.value) grantedPermissionsCount++
            }
            if (permissions.size == grantedPermissionsCount) permissionsResult.value = "All Permissions Granted" else showRationaleAlert.value = true
        }
        val context = LocalContext.current

        if (showRationaleAlert.value) {
            SimpleAlertDialog(
                title = "Grant Camera Permissions",
                message = "Please grant this permissions for this App to work properly!",
                positiveBtnText = "Settings",
                negativeBtnText = "Cancel",
                positiveBtnAction = {
                    showRationaleAlert.value = false
                    showAppSettings(context)
                },
                negativeBtnAction = { showRationaleAlert.value = false },
                dismissAction = { showRationaleAlert.value = false } // If false then dialog cancels on touch of scrim
            )
        }

        DefaultButton(actionText = "Grant Single Permission") { composeSinglePermissionResult.launch(Manifest.permission.CAMERA) }
        DefaultButton(actionText = "Grant Multiple Permissions") { composeMultiplePermissionResult.launch(permissionsArray) }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposePermissionsPreview() = ComposablesApp { ComposePermissions() }