package com.example.chonstay_android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chonstay_android.ui.LoginScreen
import com.example.chonstay_android.ui.ModeSelectScreen
import com.example.chonstay_android.ui.OnBoardingScreen
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
//        composable(
//            route = "DisasterDetailScreen/{image}/{text}",
//            arguments = listOf(
//                navArgument("image") { type = NavType.StringType },
//                navArgument("text") { type = NavType.StringType },
//            )
//        ) { backStackEntry ->
//            val image = backStackEntry.arguments?.getString("image").toString()
//            val text = backStackEntry.arguments?.getString("text").toString()
//
//            DisasterDetailScreen(image, text)
//        }
    }
}