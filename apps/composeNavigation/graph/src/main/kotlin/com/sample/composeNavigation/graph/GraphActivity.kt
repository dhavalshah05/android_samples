package com.sample.composeNavigation.graph

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.fynd.nitrozen.theme.NitrozenTheme

class GraphActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NitrozenTheme {
                NavigationGraphRoot()
            }
        }
    }

}