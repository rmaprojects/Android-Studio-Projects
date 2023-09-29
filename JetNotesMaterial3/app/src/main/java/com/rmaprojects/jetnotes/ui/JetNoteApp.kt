package com.rmaprojects.jetnotes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rmaprojects.jetnotes.navigation.Screen
import com.rmaprojects.jetnotes.ui.screens.home.HomeScreen
import com.rmaprojects.jetnotes.ui.screens.insertnote.InsertNoteScreen
import com.rmaprojects.jetnotes.ui.screens.searchnote.SearchNoteScreen
import com.rmaprojects.jetnotes.ui.screens.settings.SettingsScreen

@Composable
fun JetNoteApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                goToAddNote = { navController.navigate(Screen.InsertEditNote.route) },
                goToEditNote = { noteId ->
                    navController.navigate(
                        Screen.InsertEditNote.createRoute(
                            noteId
                        )
                    )
                },
                goToSearch = { navController.navigate(Screen.SearchNote.route) },
                goToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }

        composable(Screen.SearchNote.route) {
            SearchNoteScreen(
                navigateUp = { navController.navigateUp() },
                goToEditNote = { noteId ->
                    navController.navigate(
                        Screen.InsertEditNote.createRoute(
                            noteId
                        )
                    )
                }
            )
        }

        composable(
            route = Screen.InsertEditNote.route,
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            InsertNoteScreen(
                navigateUp = { navController.navigateUp() },
                navigateToDashboard = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                    }
                }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                navigateUp = { navController.navigateUp() }
            )
        }
    }
}