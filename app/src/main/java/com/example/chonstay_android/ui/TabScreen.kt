package com.example.chonstay_android.ui

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "AutoboxingStateValueProperty")
@Composable
fun TabScreen(navController: NavController) {
    val tabs = listOf(Icons.Filled.Home, Icons.Filled.LocationOn, Icons.Filled.AccountCircle)
    val pagerState = remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            TabRow(
                selectedTabIndex = pagerState.value,
                containerColor = Color(230, 220, 255, 255),
            ) {
                tabs.forEachIndexed { index, icon ->
                    Tab(
                        selected = pagerState.value == index,
                        onClick = { pagerState.value = index },
                        icon = { Icon(imageVector = icon, contentDescription = "") }
                    )
                }
            }
        }
    ) {
        when (pagerState.value) {
            0 -> MainScreen(navController)
            1 -> MapScreen(navController)
            2 -> UserScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTabLayout() {
    TabScreen(rememberNavController())
}