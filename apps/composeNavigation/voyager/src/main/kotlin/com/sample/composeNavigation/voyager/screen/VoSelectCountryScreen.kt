package com.sample.composeNavigation.voyager.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.sample.composeNavigation.common.screen.selectCountry.SelectCountryScreenViewModel
import com.sample.composeNavigation.common.screen.selectCountry.SelectCountryScreenWrapper
import com.sample.composeNavigation.voyager.hideWithResult

class VoSelectCountryScreen : Screen {

    override val key: ScreenKey
        get() = this.javaClass.name

    @Composable
    override fun Content() {
        val viewModel = viewModel<SelectCountryScreenViewModel>()
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        SelectCountryScreenWrapper(
            viewModel = viewModel,
            onGoBackWithResult = {
                bottomSheetNavigator.hideWithResult(it, key)
            }
        )
    }
}