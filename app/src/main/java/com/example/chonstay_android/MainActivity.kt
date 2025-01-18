package com.example.chonstay_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gdgoc_team6.ui.theme.GDGoC_team6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GDGoC_team6Theme {
                Navigator()
            }
        }
    }
}