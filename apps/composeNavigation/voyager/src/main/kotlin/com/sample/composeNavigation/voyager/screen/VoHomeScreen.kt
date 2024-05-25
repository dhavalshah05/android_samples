package com.sample.composeNavigation.voyager.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.sample.composeNavigation.common.screen.home.HomeScreenViewModel
import com.sample.composeNavigation.common.screen.home.HomeScreenWrapper
import com.sample.composeNavigation.voyager.getResult

data class VoHomeScreen(
    private val token: String
) : Screen {

    override val key: ScreenKey
        get() = this.javaClass.name

    @Composable
    override fun Content() {
        val homeViewModel = viewModel<HomeScreenViewModel>()
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        val context = LocalContext.current

        val result = bottomSheetNavigator.getResult<String>(screenKey = VoSelectCountryScreen::class.java.name)

        LaunchedEffect(key1 = token) {
            homeViewModel.setup(token)
        }

        LaunchedEffect(key1 = result.value) {
            if (!result.value.isNullOrBlank()) {
                Toast.makeText(context, result.value, Toast.LENGTH_SHORT).show()
            }
        }

        HomeScreenWrapper(
            viewModel = homeViewModel,
            onSelectCountryClick = {
                bottomSheetNavigator.show(VoSelectCountryScreen())
            }
        )
    }
}