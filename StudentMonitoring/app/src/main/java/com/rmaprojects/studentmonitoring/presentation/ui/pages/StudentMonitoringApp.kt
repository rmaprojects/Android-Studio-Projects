package com.rmaprojects.studentmonitoring.presentation.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rmaprojects.studentmonitoring.core.data.source.local.kotpref.SharedPreference
import com.rmaprojects.studentmonitoring.presentation.navigation.Screens
import com.rmaprojects.studentmonitoring.presentation.ui.pages.auth.AuthScreen
import com.rmaprojects.studentmonitoring.presentation.ui.pages.history.HistoryScreen
import com.rmaprojects.studentmonitoring.presentation.ui.pages.home.HomeScreen

@Composable
fun StudentMonitoringApp(
    navController: NavHostController = rememberNavController()
) {

    LaunchedEffect(true) {
        if (SharedPreference.savedUuid != null) {
            navController.navigate(Screens.Home.route)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screens.Auth.route
    ) {
        composable(Screens.Auth.route) {
            AuthScreen(
                goToHomeScreen = {
                    navController.popBackStack(inclusive = true, route =  Screens.Home.route)
                }
            )
        }
        composable(Screens.History.route) {
            HistoryScreen()
        }
        composable(Screens.Home.route) {
            HomeScreen(
                goToHistoryScreen = {
                    navController.navigate(Screens.History.route)
                },
                backToAuth = {
                    navController.popBackStack(inclusive = true, route = Screens.Auth.route)
                }
            )
        }
    }
}