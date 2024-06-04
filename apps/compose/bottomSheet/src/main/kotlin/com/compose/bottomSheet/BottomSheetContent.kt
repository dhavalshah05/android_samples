package com.compose.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fynd.nitrozen.theme.NitrozenTheme

@Composable
fun BottomSheetContent() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(NitrozenTheme.colors.background)
            .padding(20.dp)
    ) {
        items(100) {
            Text(
                text = "Sheet Item ${it.plus(1)}",
                style = NitrozenTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}