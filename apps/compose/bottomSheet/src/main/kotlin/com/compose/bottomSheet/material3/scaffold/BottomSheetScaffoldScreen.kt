package com.compose.bottomSheet.material3.scaffold

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.compose.bottomSheet.BottomSheetContent
import com.compose.bottomSheet.ScreenContent
import com.fynd.nitrozen.components.appbar.NitrozenAppBar
import com.fynd.nitrozen.theme.NitrozenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScaffoldScreen(
    onBackClick: () -> Unit,
) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        sheetContent = {
            BottomSheetContent()
        },
        sheetPeekHeight = 150.dp,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = NitrozenTheme.colors.primary20,
        sheetContainerColor = NitrozenTheme.colors.background,
        sheetShadowElevation = 4.dp,
        scaffoldState = scaffoldState,
        topBar = {
            NitrozenAppBar(
                title = "M3 BottomSheetScaffold",
                leading = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        },
    ) {
        ScreenContent(it)
    }
}



