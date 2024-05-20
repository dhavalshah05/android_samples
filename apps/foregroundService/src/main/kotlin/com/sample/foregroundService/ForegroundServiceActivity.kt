package com.sample.foregroundService

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.common.logging.MyLogger
import com.fynd.nitrozen.components.appbar.NitrozenAppBar
import com.fynd.nitrozen.components.button.text.NitrozenTextButton
import com.fynd.nitrozen.components.dialog.Default
import com.fynd.nitrozen.components.dialog.NitrozenDialog
import com.fynd.nitrozen.components.dialog.NitrozenDialogConfiguration
import com.fynd.nitrozen.theme.NitrozenTheme
import com.sample.foregroundService.common.runtimePermissions.rememberSinglePermissionLauncher

class ForegroundServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NitrozenTheme {
                ForegroundServiceScreen()
            }
        }
    }

    @Composable
    private fun ForegroundServiceScreen() {
        val notificationsPermissionLauncher = rememberSinglePermissionLauncher(permission = android.Manifest.permission.POST_NOTIFICATIONS)

        LaunchedEffect(notificationsPermissionLauncher) {
            if (notificationsPermissionLauncher.hasPermission) {
                MyLogger.d("Permission Granted")
            } else {
                MyLogger.d("Permission Not Granted")
            }
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            ) {
                NitrozenTextButton(
                    text = "Start Service",
                    onClick = {
                        if (notificationsPermissionLauncher.hasPermission) {
                            startTimerService()
                        } else {
                            notificationsPermissionLauncher.requestPermission()
                        }
                    }
                )

                NitrozenTextButton(
                    text = "Stop Service",
                    onClick = {
                        stopTimerService()
                    }
                )
            }

            if (notificationsPermissionLauncher.showRational) {
                NitrozenDialog(
                    title = "Notification Permission Required",
                    subTitle = "Give notifications access to use this app",
                    onDismissRequest = {
                        notificationsPermissionLauncher.hideRational()
                    },
                    configuration = NitrozenDialogConfiguration.Default.copy(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true,
                    ),
                    positiveLabel = "OK",
                    negativeLabel = "Cancel",
                    positiveButtonClick = {
                        notificationsPermissionLauncher.hideRational()
                        notificationsPermissionLauncher.requestPermission()
                    },
                    negativeButtonClick = {
                        notificationsPermissionLauncher.hideRational()
                    }
                )
            }

            if (notificationsPermissionLauncher.showSettings) {
                NitrozenDialog(
                    title = "Notification Permission Required",
                    subTitle = "Open settings to give access to permission",
                    onDismissRequest = {
                        notificationsPermissionLauncher.hideSettings()
                    },
                    configuration = NitrozenDialogConfiguration.Default.copy(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true,
                    ),
                    positiveLabel = "Open Settings",
                    positiveButtonClick = {
                        notificationsPermissionLauncher.hideSettings()
                        openAppSettings()
                    }
                )
            }
        }
    }

    private fun stopTimerService() {
        CounterService.stopService(this)
    }

    private fun startTimerService() {
        CounterService.startService(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        MyLogger.d("Activity Destroyed")
    }

    private fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        with(intent) {
            data = Uri.fromParts("package", packageName, null)
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        }

        startActivity(intent)
    }
}