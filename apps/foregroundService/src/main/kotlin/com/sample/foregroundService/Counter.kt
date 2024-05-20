package com.sample.foregroundService

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

object Counter {
    private val _currentValue = MutableStateFlow(0)
    val currentValue = _currentValue.asStateFlow()

    private var isRunning = false
    private val scope = CoroutineScope(Dispatchers.Main)

    fun startTimer() {
        if (isRunning) {
            return
        }

        isRunning = true
        scope.launch {
            _currentValue.update { 0 }
            while (isRunning) {
                delay(1000)
                _currentValue.update { _currentValue.value.plus(1) }
            }
        }
    }

    fun stopCounter() {
        scope.coroutineContext.cancelChildren()
        _currentValue.update { 0 }
        isRunning = false
    }
}