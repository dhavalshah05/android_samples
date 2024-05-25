package com.sample.composeNavigation.voyager.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.sample.composeNavigation.common.screen.splash.SplashScreenViewModel
import com.sample.composeNavigation.common.screen.splash.SplashScreenWrapper


class VoSplashScreen : Screen {

    override val key: ScreenKey
        get() = this.javaClass.name

    @Composable
    override fun Content() {
        val splashViewModel = viewModel<SplashScreenViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        SplashScreenWrapper(
            viewModel = splashViewModel,
            onNavigateToHome = {
                navigator.replace(VoHomeScreen(token = "Abc@123#"))
            }
        )
    }
}