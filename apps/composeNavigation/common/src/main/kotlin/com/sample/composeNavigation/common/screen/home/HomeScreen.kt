package com.sample.composeNavigation.common.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.fynd.nitrozen.components.appbar.NitrozenAppBar
import com.fynd.nitrozen.components.button.filled.NitrozenFilledButton
import com.fynd.nitrozen.theme.NitrozenTheme

@Composable
fun HomeScreenWrapper(
    viewModel: HomeScreenViewModel,
    onSelectCountryClick: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    HomeScreen(
        screenState = viewModel.screenState.collectAsState().value,
        onSelectCountryClick = onSelectCountryClick
    )
}

@Composable
private fun HomeScreen(
    screenState: HomeScreenState,
    onSelectCountryClick: () -> Unit,
) {

    Scaffold(
        topBar = {
            NitrozenAppBar(title = "Home Screen")
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Home Screen",
                style = NitrozenTheme.typography.headingXs
            )

            Text(
                text = screenState.token,
                style = NitrozenTheme.typography.bodySmall
            )

            NitrozenFilledButton(text = "Select Country", onClick = onSelectCountryClick)
        }
    }
}