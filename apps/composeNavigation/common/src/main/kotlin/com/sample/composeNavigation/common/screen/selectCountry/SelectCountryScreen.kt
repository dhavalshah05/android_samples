package com.sample.composeNavigation.common.screen.selectCountry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.fynd.nitrozen.components.appbar.NitrozenAppBar
import com.fynd.nitrozen.components.button.outlined.NitrozenOutlinedButton
import com.fynd.nitrozen.components.textfield.outlined.NitrozenOutlinedTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SelectCountryScreenWrapper(
    viewModel: SelectCountryScreenViewModel,
    onGoBackWithResult: (String) -> Unit,
) {
    val lifecycle = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.goBackWithResult.collectLatest {
                onGoBackWithResult(it)
            }
        }
    }

    SelectCountryScreen(
        screenState = viewModel.screenState.collectAsState().value,
        onCountryTextChange = viewModel::onCountryTextChange,
        onDoneClick = viewModel::onDoneClick
    )
}

@Composable
private fun SelectCountryScreen(
    screenState: SelectCountryScreenState,
    onCountryTextChange: (String) -> Unit,
    onDoneClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            NitrozenAppBar(title = "Select Country")
        },
        modifier = Modifier
            .fillMaxHeight(fraction = 0.9f)
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            NitrozenOutlinedTextField(
                value = screenState.country,
                onValueChange = onCountryTextChange,
                hint = "Country"
            )

            NitrozenOutlinedButton(text = "Done", onClick = onDoneClick)
        }
    }
}