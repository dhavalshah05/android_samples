package com.compose.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fynd.nitrozen.components.appbar.NitrozenAppBar

@Composable
fun BottomSheetScreen() {
    Scaffold(
        topBar = {
            NitrozenAppBar(title = "Bottom Sheet")
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {

        }
    }
}