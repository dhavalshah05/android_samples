package com.sample.runtimePermission

import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext

data class PermissionLauncher(
    val hasPermission: Boolean,
    val showRational: Boolean,
    val showSettingsToGrantPermission: Boolean,
    val requestPermissions: () -> Unit,
    val hideRational: () -> Unit,
    val hideSettingsToGrantPermission: () -> Unit,
)

@Composable
fun rememberCameraPermissionLauncher(): PermissionLauncher {
    val context = LocalContext.current

    val showCameraRational = rememberSaveable {
        mutableStateOf(false)
    }

    val showSettingsOptionForPermissions = rememberSaveable {
        mutableStateOf(false)
    }

    val hasCameraPermission = rememberSaveable {
        mutableStateOf(false)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { _ ->

        val activity = context as ComponentActivity
        hasCameraPermission.value = context.hasCameraPermission()
        showCameraRational.value = activity.shouldShowCameraPermissionRational()

        if (!hasCameraPermission.value && !showCameraRational.value) {
            showSettingsOptionForPermissions.value = true
        }
    }

    return rememberSaveable(
        hasCameraPermission.value,
        showCameraRational.value,
        showSettingsOptionForPermissions.value
    ) {
        PermissionLauncher(
            hasPermission = hasCameraPermission.value,
            showRational = showCameraRational.value,
            showSettingsToGrantPermission = showSettingsOptionForPermissions.value,
            requestPermissions = {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            },
            hideRational = {
                showCameraRational.value = false
            },
            hideSettingsToGrantPermission = {
                showSettingsOptionForPermissions.value = false
            }
        )
    }
}