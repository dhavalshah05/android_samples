package com.compose.bottomSheet.material2.scaffold

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.bottomSheet.BottomSheetContent
import com.compose.bottomSheet.ScreenContent
import com.fynd.nitrozen.components.appbar.NitrozenAppBar
import com.fynd.nitrozen.theme.NitrozenTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScaffoldScreen(
    onBackClick: () -> Unit,
) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        sheetContent = {
            BottomSheetContent()
        },
        sheetGesturesEnabled = true,
        sheetPeekHeight = 150.dp,
        sheetElevation = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetBackgroundColor = Color.Transparent,
        backgroundColor = NitrozenTheme.colors.primary20,
        scaffoldState = scaffoldState,
        topBar = {
            NitrozenAppBar(
                title = "M2 BottomSheetScaffold",
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



