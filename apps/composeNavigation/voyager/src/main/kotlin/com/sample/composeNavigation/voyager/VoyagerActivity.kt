package com.sample.composeNavigation.voyager

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.fynd.nitrozen.theme.NitrozenTheme
import com.sample.composeNavigation.voyager.screen.VoSplashScreen

@OptIn(ExperimentalMaterialApi::class)
class VoyagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NitrozenTheme {
                BottomSheetNavigator(
                    sheetShape = MaterialTheme.shapes.large,
                    scrimColor = Color.Black.copy(alpha = 0.5f)
                ) {
                    Navigator(screen = VoSplashScreen()) {
                        SlideTransition(navigator = it)
                    }
                }
            }
        }
    }

}