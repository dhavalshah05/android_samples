package com.compose.bottomSheet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.compose.bottomSheet.material3.scaffold.BottomSheetScaffoldScreen
import com.fynd.nitrozen.theme.NitrozenTheme

class BottomSheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NitrozenTheme {
                BottomSheetScaffoldScreen {

                }
            }
        }
    }

}