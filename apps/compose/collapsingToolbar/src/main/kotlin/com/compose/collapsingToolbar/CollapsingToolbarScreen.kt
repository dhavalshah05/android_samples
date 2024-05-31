package com.compose.collapsingToolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.fynd.nitrozen.theme.NitrozenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingToolbarScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            FlexibleTopBar(
                scrollBehavior = scrollBehavior,
                colors = FlexibleTopBarDefaults.topAppBarColors(
                    containerColor = NitrozenTheme.colors.primary20,
                    scrolledContainerColor = NitrozenTheme.colors.primary20,
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(NitrozenTheme.colors.primary40)
                    )

                    Text(
                        text = "Jon Snow",
                        style = NitrozenTheme.typography.headingXs
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(50) {
                Text(
                    text = "Item ${it.plus(1)}",
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }
}