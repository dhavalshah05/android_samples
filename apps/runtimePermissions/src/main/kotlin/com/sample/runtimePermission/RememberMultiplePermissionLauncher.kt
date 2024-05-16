package com.sample.runtimePermission

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext

data class MultiplePermissionLauncher(
    val hasAllPermissions: Boolean,
    val showRational: Boolean,
    val showSettings: Boolean,
    private val requestPermissions: () -> Unit,
    private val hideRational: () -> Unit,
    private val hideSettings: () -> Unit,
) {
    fun requestPermissions() {
        requestPermissions.invoke()
    }

    fun hideRational() {
        hideRational.invoke()
    }

    fun hideSettings() {
        hideSettings.invoke()
    }
}

@Composable
fun rememberMultiplePermissionLauncher(
    permissions: List<String>
): MultiplePermissionLauncher {

    val context = LocalContext.current
    val activity = context as ComponentActivity

    val showRational = rememberSaveable {
        mutableStateOf(false)
    }

    val showSettings = rememberSaveable {
        mutableStateOf(false)
    }

    val hasAllPermissions = rememberSaveable {
        mutableStateOf(false)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { _ ->
        hasAllPermissions.value = permissions.map { context.hasPermission(it) }.all { it }
        showRational.value = permissions.map { activity.shouldShowRequestPermissionRationale(it) }.any { it }

        if (!hasAllPermissions.value && !showRational.value) {
            showSettings.value = true
        }
    }

    return remember(
        hasAllPermissions.value,
        showRational.value,
        showSettings.value
    ) {
        MultiplePermissionLauncher(
            hasAllPermissions = hasAllPermissions.value,
            showRational = showRational.value,
            showSettings = showSettings.value,
            requestPermissions = {
                val netPermission = permissions.filter { !context.hasPermission(it) }
                permissionLauncher.launch(netPermission.toTypedArray())
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