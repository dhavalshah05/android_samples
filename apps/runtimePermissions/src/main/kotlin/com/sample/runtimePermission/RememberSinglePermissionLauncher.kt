package com.sample.runtimePermission

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext

data class SinglePermissionLauncher(
    val hasPermission: Boolean,
    val showRational: Boolean,
    val showSettings: Boolean,
    private val requestPermission: () -> Unit,
    private val hideRational: () -> Unit,
    private val hideSettings: () -> Unit,
) {
    fun requestPermission() {
        requestPermission.invoke()
    }

    fun hideRational() {
        hideRational.invoke()
    }

    fun hideSettings() {
        hideSettings.invoke()
    }
}

@Composable
fun rememberSinglePermissionLauncher(permission: String): SinglePermissionLauncher {
    val context = LocalContext.current

    val showRational = rememberSaveable {
        mutableStateOf(false)
    }

    val showSettings = rememberSaveable {
        mutableStateOf(false)
    }

    val hasPermission = rememberSaveable {
        mutableStateOf(false)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { _ ->

        val activity = context as ComponentActivity
        hasPermission.value = context.hasPermission(permission)
        showRational.value = activity.shouldShowRequestPermissionRationale(permission)

        if (!hasPermission.value && !showRational.value) {
            showSettings.value = true
        }
    }

    return remember(
        hasPermission.value,
        showRational.value,
        showSettings.value
    ) {
        SinglePermissionLauncher(
            hasPermission = hasPermission.value,
            showRational = showRational.value,
            showSettings = showSettings.value,
            requestPermission = {
                permissionLauncher.launch(permission)
            },
            hideRational = {
                showRational.value = false
            },
            hideSettings = {
                showSettings.value = false
            }
        )
    }
}