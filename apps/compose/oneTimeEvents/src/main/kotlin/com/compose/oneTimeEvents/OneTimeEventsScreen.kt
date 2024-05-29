package com.compose.oneTimeEvents

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.fynd.nitrozen.components.button.text.NitrozenTextButton

@Composable
fun OneTimeEventsScreenWrapper(
    viewModel: OneTimeEventsViewModel
) {
    val context = LocalContext.current

    ObserveAsEvent(
        flow = viewModel.showToast,
        onEvent = {
            Toast.makeText(
                context,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }
    )

    OneTimeEventsScreen(
        showToastClicked = {
            viewModel.onShowToastEvent()
        }
    )
}

@Composable
private fun OneTimeEventsScreen(
    showToastClicked: () -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            NitrozenTextButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Show Toast",
                onClick = showToastClicked
            )
        }
    }
}