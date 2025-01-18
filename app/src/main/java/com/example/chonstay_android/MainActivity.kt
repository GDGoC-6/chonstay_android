package com.example.chonstay_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

object Value {
    var stayList: List<HomePreview> = emptyList()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator()
        }
    }
}