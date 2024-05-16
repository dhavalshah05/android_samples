package com.sample.runtimePermission

import android.Manifest
import android.content.Intent
import android.content.Intent.CATEGORY_DEFAULT
import android.content.Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.common.logging.MyLogger
import com.fynd.nitrozen.components.appbar.NitrozenAppBar
import com.fynd.nitrozen.components.button.text.NitrozenTextButton
import com.fynd.nitrozen.components.dialog.Default
import com.fynd.nitrozen.components.dialog.NitrozenDialog
import com.fynd.nitrozen.components.dialog.NitrozenDialogConfiguration
import com.fynd.nitrozen.theme.NitrozenTheme
import com.sample.runtimePermissions.R

class RuntimePermissionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NitrozenTheme {
                RuntimePermissionScreen()
            }
        }
    }

    @Composable
    private fun RuntimePermissionScreen() {
        val permissionLauncher = rememberMultiplePermissionLauncher(
            listOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION)
        )

        LaunchedEffect(permissionLauncher) {
            MyLogger.d("""
                permissionLauncher - $permissionLauncher
            """.trimIndent())
        }

        Scaffold(
            backgroundColor = NitrozenTheme.colors.background,
            topBar = {
                NitrozenAppBar(title = getString(R.string.app_name))
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                NitrozenTextButton(
                    text = "Open Camera",
                    onClick = {
                        permissionLauncher.requestPermissions()
                    }
                )
            }

            if (permissionLauncher.showRational) {
                NitrozenDialog(
                    title = "Camera access is required",
                    subTitle = "Give camera access to use this app",
                    onDismissRequest = {
                        permissionLauncher.hideRational.invoke()
                    },
                    configuration = NitrozenDialogConfiguration.Default.copy(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true,
                    ),
                    positiveLabel = "OK",
                    negativeLabel = "Cancel",
                    positiveButtonClick = {
                        permissionLauncher.hideRational.invoke()
                        permissionLauncher.requestPermissions.invoke()
                    },
                    negativeButtonClick = {
                        permissionLauncher.hideRational.invoke()
                    }
                )
            }

            if (permissionLauncher.showSettings) {
                NitrozenDialog(
                    title = "Camera access is required",
                    subTitle = "Open settings to give access to permission",
                    onDismissRequest = {
                        permissionLauncher.hideSettings.invoke()
                    },
                    configuration = NitrozenDialogConfiguration.Default.copy(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true,
                    ),
                    positiveLabel = "Open Settings",
                    positiveButtonClick = {
                        permissionLauncher.hideSettings.invoke()
                        openAppSettings()
                    }
                )
            }
        }
    }

    private fun openAppSettings() {
        val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS)
        with(intent) {
            data = Uri.fromParts("package", packageName, null)
            addCategory(CATEGORY_DEFAULT)
            addFlags(FLAG_ACTIVITY_NEW_TASK)
            addFlags(FLAG_ACTIVITY_NO_HISTORY)
            addFlags(FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        }

        startActivity(intent)
    }
}