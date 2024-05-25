package com.sample.composeNavigation.common.screen.selectCountry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SelectCountryScreenViewModel : ViewModel() {

    private val _screenState = MutableStateFlow(SelectCountryScreenState())
    val screenState = _screenState.asStateFlow()

    private val _goBackWithResult = Channel<String>()
    val goBackWithResult = _goBackWithResult.receiveAsFlow()

    fun onCountryTextChange(text: String) {
        _screenState.update { it.copy(country = text) }
    }

    fun onDoneClick() {
        viewModelScope.launch {
            _goBackWithResult.send(_screenState.value.country)
        }
    }
}