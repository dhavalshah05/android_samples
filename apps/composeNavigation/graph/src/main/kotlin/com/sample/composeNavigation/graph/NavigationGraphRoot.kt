package com.sample.composeNavigation.graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.composeNavigation.common.screen.home.HomeScreenViewModel
import com.sample.composeNavigation.common.screen.home.HomeScreenWrapper
import com.sample.composeNavigation.common.screen.splash.SplashScreenViewModel
import com.sample.composeNavigation.common.screen.splash.SplashScreenWrapper

@Composable
fun NavigationGraphRoot() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = Modifier.fillMaxSize(),
        route = "hostGraph",
        startDestination = "splash"
    ) {
        composable(route = "splash") {
            val splashViewModel = viewModel<SplashScreenViewModel>()

            SplashScreenWrapper(
                viewModel = splashViewModel,
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo(route = "hostGraph") {
                            inclusive = false
                        }
                    }
                }
            )
        }

        composable(route = "home") {
            val homeViewModel = viewModel<HomeScreenViewModel>()

            HomeScreenWrapper(
                viewModel = homeViewModel,
                onSelectCountryClick = {

                }
            )
        }
    }
}