package com.compose.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fynd.nitrozen.theme.NitrozenTheme

@Composable
fun ScreenContent(it: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(NitrozenTheme.colors.primary20)
    ) {
        Text(
            text = "Screen Content",
            style = NitrozenTheme.typography.bodySmall
        )
    }
}