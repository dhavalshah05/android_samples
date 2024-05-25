package com.sample.composeNavigation.common.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val _screenState = MutableStateFlow(HomeScreenState())
    val screenState = _screenState.asStateFlow()

    fun setup(token: String) {
        viewModelScope.launch {
            _screenState.update { it.copy(token = token) }
        }
    }
}