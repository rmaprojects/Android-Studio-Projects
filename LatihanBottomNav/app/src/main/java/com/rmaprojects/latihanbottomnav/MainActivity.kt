package com.rmaprojects.latihanbottomnav

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rmaprojects.latihanbottomnav.ui.screens.FavouriteScreen
import com.rmaprojects.latihanbottomnav.ui.screens.HomeScreen
import com.rmaprojects.latihanbottomnav.ui.screens.ProfileScreen
import com.rmaprojects.latihanbottomnav.ui.screens.SearchScreen
import com.rmaprojects.latihanbottomnav.ui.screens.SettingsScreen
import com.rmaprojects.latihanbottomnav.ui.theme.LatihanBottomNavTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pressed = 0
        setContent {

            BackHandler {
                Toast.makeText(this, "Klik back 2x", Toast.LENGTH_SHORT).show()
                pressed++
                if (pressed == 2) {
                    finish()
                }
            }

            LatihanBottomNavTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    Scaffold(
                        modifier = Modifier,
                        bottomBar = {
                            NavigationBar {
                                bottomNavItemList.map { item ->
                                    NavigationBarItem(
                                        colors = NavigationBarItemDefaults.colors(
                                            indicatorColor = Color.Transparent
                                        ),
                                        selected = currentRoute == item.route,
                                        onClick = {
                                            navController.navigate(item.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                restoreState = true
                                                launchSingleTop = true
                                            }
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = item.icon,
                                                contentDescription = item.label
                                            )
                                        },
                                        label = {
                                            Text(text = item.label)
                                        },
                                        alwaysShowLabel = false
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            startDestination = "home"
                        ) {
                            composable("home") {
                                HomeScreen()
                            }
                            composable("favourite") {
                                FavouriteScreen()
                            }
                            composable("profile") {
                                ProfileScreen()
                            }
                            composable("search") {
                                SearchScreen()
                            }
                            composable("settings") {
                                SettingsScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}