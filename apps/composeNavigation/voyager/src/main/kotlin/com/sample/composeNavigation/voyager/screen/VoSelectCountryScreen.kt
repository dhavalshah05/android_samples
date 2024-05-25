package com.sample.composeNavigation.voyager.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import com.sample.composeNavigation.common.screen.selectCountry.SelectCountryScreenViewModel
import com.sample.composeNavigation.common.screen.selectCountry.SelectCountryScreenWrapper

class VoSelectCountryScreen : Screen {

    override val key: ScreenKey
        get() = uniqueScreenKey

    @Composable
    override fun Content() {
        val viewModel = viewModel<SelectCountryScreenViewModel>()

        SelectCountryScreenWrapper(
            viewModel = viewModel,
            onGoBackWithResult = {

            }
        )
    }
}