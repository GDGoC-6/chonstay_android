package com.example.chonstay_android.ui.Owner

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "AutoboxingStateValueProperty")
@Composable
fun OwnerTabScreen(navController: NavController) {
    val tabs = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.AccountCircle)
    val pagerState = remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            TabRow(
                selectedTabIndex = pagerState.value,
                containerColor = Color(230, 230, 230, 255),
                indicator = { tabPositions ->
                    SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[pagerState.value]),
                        color = Color(107, 142, 35)
                    )
                }
            ) {
                tabs.forEachIndexed { index, icon ->
                    Tab(
                        selected = pagerState.value == index,
                        onClick = { pagerState.value = index },
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = "",
                                tint = if (pagerState.value == index) Color(107, 142, 35)
                                else Color.LightGray
                            )
                        }
                    )
                }
            }
        }
    ) {
        when (pagerState.value) {
            0 -> ReservationListScreen()
            1 -> StayInfoScreen()
            2 -> OwnerUserScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTabLayout() {
    OwnerTabScreen(rememberNavController())
}