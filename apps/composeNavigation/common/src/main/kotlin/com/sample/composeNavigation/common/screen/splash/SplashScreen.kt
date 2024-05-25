package com.sample.composeNavigation.common.screen.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.fynd.nitrozen.theme.NitrozenTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreenWrapper(
    viewModel: SplashScreenViewModel,
    onNavigateToHome: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.navigateToHome.collectLatest {
                onNavigateToHome()
            }
        }
    }

    SplashScreen()
}

@Composable
private fun SplashScreen() {

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Splash Screen",
                style = NitrozenTheme.typography.headingXs
            )
        }
    }
}