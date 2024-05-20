package com.sample.foregroundService

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.fynd.nitrozen.components.appbar.NitrozenAppBar
import com.fynd.nitrozen.theme.NitrozenTheme

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
        Scaffold(
            backgroundColor = NitrozenTheme.colors.background,
            topBar = {
                NitrozenAppBar(title = getString(R.string.app_name))
            }
        ) { _ ->

        }
    }
}