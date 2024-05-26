package com.sample.composeNavigation.graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sample.composeNavigation.common.screen.home.HomeScreenViewModel
import com.sample.composeNavigation.common.screen.home.HomeScreenWrapper
import com.sample.composeNavigation.common.screen.splash.SplashScreenViewModel
import com.sample.composeNavigation.common.screen.splash.SplashScreenWrapper
import kotlinx.serialization.Serializable

@Serializable
object HostGraph

@Serializable
object SplashScreen

@Serializable
data class HomeScreen(val token: String)

@Composable
fun NavigationGraphRoot() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = Modifier.fillMaxSize(),
        startDestination = SplashScreen::class,
        route = HostGraph::class,
    ) {
        composable<SplashScreen> {
            val splashViewModel = viewModel<SplashScreenViewModel>()

            SplashScreenWrapper(
                viewModel = splashViewModel,
                onNavigateToHome = {
                    navController.navigate(HomeScreen("Abc@123#")) {
                        popUpTo(HostGraph) {
                            inclusive = false
                        }
                    }
                }
            )
        }

        composable<HomeScreen> {
            val homeScreen = it.toRoute<HomeScreen>()
            val homeViewModel = viewModel<HomeScreenViewModel>()

            LaunchedEffect(key1 = homeViewModel) {
                homeViewModel.setup(homeScreen.token)
            }

            HomeScreenWrapper(
                viewModel = homeViewModel,
                onSelectCountryClick = {

                }
            )
        }
    }
}