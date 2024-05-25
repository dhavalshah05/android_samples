package com.sample.composeNavigation.common.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _navigateToHome = Channel<Unit>()
    val navigateToHome = _navigateToHome.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            _navigateToHome.send(Unit)
        }
    }

}