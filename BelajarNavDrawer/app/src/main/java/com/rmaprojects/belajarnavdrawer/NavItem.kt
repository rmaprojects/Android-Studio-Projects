package com.rmaprojects.belajarnavdrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val navigationDrawerItemList = listOf(
    NavItem("home", "Home", Icons.Default.Home),
    NavItem("favorite", "Favorite", Icons.Default.Favorite),
    NavItem("settings", "Settings", Icons.Default.Settings)
)
