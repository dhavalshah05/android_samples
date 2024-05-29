package com.compose.oneTimeEvents

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fynd.nitrozen.theme.NitrozenTheme

class OneTimeEventsActivity : AppCompatActivity() {

    private val viewModel: OneTimeEventsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NitrozenTheme {
                OneTimeEventsScreenWrapper(
                    viewModel = viewModel
                )
            }
        }
    }

}