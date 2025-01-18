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
import com.example.chonstay_android.ui.Customer.StayDetailScreen
import com.example.chonstay_android.ui.Customer.StayListScreen
import com.example.chonstay_android.ui.Customer.CustomerTabScreen
import com.example.chonstay_android.ui.Owner.OwnerTabScreen

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
        composable("OwnerTabScreen") {
            OwnerTabScreen(navController)
        }
        composable("CustomerTabScreen") {
            CustomerTabScreen(navController)
        }
        composable(
            route = "StayListScreen/{location}",
            arguments = listOf(navArgument("location") { type = NavType.StringType })
        ) {
            val location = it.arguments?.getString("location").toString()

            StayListScreen(navController, location)
        }
        composable(
            route = "StayDetailScreen/{title}/{location}/{star}",
            arguments = listOf(navArgument("title") { type = NavType.StringType },
                navArgument("location") { type = NavType.StringType },
                navArgument("star") { type = NavType.StringType })
        ) {
            val title = it.arguments?.getString("title").toString()
            val location = it.arguments?.getString("location").toString()
            val star = it.arguments?.getString("star").toString()

            StayDetailScreen(title, location, star)
        }
    }
}