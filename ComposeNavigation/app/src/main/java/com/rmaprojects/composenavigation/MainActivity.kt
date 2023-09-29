package com.rmaprojects.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rmaprojects.composenavigation.ui.screens.Screen1
import com.rmaprojects.composenavigation.ui.screens.Screen2
import com.rmaprojects.composenavigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController: NavHostController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "screen1"
                    ) {
                        composable("screen1") {
                            Screen1(
                                goToScreen2 = {
                                    navController.navigate("screen2")
                                }
                            )
                        }
                        composable("screen2") {
                            Screen2(
                                goToScreen1 = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}