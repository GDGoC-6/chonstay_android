package com.example.chonstay_android

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chonstay_android.ui.LoginScreen
import com.example.chonstay_android.ui.ModeSelectScreen
import com.example.chonstay_android.ui.OnBoardingScreen
import com.example.chonstay_android.ui.StayDetailScreen
import com.example.chonstay_android.ui.StayListScreen
import com.example.chonstay_android.ui.TabScreen

@Composable
fun Navigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "LoginScreen") {
        composable("LoginScreen") {
            LoginScreen(navController)
        }
        composable("OnBoardingScreen") {
            OnBoardingScreen(navController)
        }
        composable("ModeSelect") {
            ModeSelectScreen(navController)
        }
        composable("TabScreen") {
            TabScreen(navController)
        }
        composable(route =  "StayListScreen/{location}",
            arguments = listOf(navArgument("location"){type = NavType.StringType})
        ) {
            val location = it.arguments?.getString("location").toString()

            StayListScreen(navController, location)
        }
        composable("StayDetailScreen") {
            StayDetailScreen()
        }
    }
}