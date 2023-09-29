package com.rmaprojects.studentmonitoring.presentation.navigation

sealed class Screens(val route: String) {
    data object Home: Screens("home")
    data object History: Screens("history")
    data object Auth: Screens("auth")
}
