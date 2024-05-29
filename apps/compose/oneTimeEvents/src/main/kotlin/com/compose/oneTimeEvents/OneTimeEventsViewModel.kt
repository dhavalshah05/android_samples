package com.compose.oneTimeEvents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OneTimeEventsViewModel : ViewModel() {

    private val _showToast = Channel<String>()
    val showToast = _showToast.receiveAsFlow()

    fun onShowToastEvent() {
        viewModelScope.launch {
            _showToast.send("Here is a toast")
        }
    }
}