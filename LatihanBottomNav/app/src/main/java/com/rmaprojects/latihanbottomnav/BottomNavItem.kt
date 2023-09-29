package com.rmaprojects.latihanbottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val bottomNavItemList = listOf(
    BottomNavItem("Home", Icons.Default.Home, "home"),
    BottomNavItem("Favourite", Icons.Default.Favorite, "favourite"),
    BottomNavItem("Search", Icons.Default.Search, "search"),
    BottomNavItem("Settings", Icons.Default.Settings, "settings"),
    BottomNavItem("Profile", Icons.Default.Person, "profile"),
)
