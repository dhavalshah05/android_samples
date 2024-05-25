package com.sample.composeNavigation.voyager.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.sample.composeNavigation.common.screen.home.HomeScreenViewModel
import com.sample.composeNavigation.common.screen.home.HomeScreenWrapper

data class VoHomeScreen(
    private val token: String
) : Screen {

    override val key: ScreenKey
        get() = uniqueScreenKey

    @Composable
    override fun Content() {
        val homeViewModel = viewModel<HomeScreenViewModel>()
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        HomeScreenWrapper(
            viewModel = homeViewModel,
            onSelectCountryClick = {
                bottomSheetNavigator.show(VoSelectCountryScreen())
            }
        )
    }
}