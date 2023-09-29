package com.rmaprojects.studentmonitoring.presentation.ui.pages.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmaprojects.studentmonitoring.presentation.ui.pages.auth.event.AuthEvent
import com.rmaprojects.studentmonitoring.presentation.ui.pages.auth.content.LoginContent
import com.rmaprojects.studentmonitoring.presentation.ui.pages.auth.content.RegisterContent

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    goToHomeScreen: () -> Unit
) {

    var isRegister by remember {
        mutableStateOf(false)
    }

    val loginState by viewModel.loginState.collectAsState()
    val registerState by viewModel.registerState.collectAsState()

    Scaffold { innerPadding ->
        AnimatedVisibility(visible = isRegister) {
            RegisterContent(
                modifier = Modifier.padding(innerPadding),
                goToLogin = {
                    isRegister = false
                },
                executeRegister = { name, email, password ->
                    viewModel.onEvent(AuthEvent.RegisterUser(name, email, password))
                },
                registerState = registerState,
                revertBackToNormalState = {
                    viewModel.onEvent(AuthEvent.DismissDialog)
                },
                goToHomeScreen = goToHomeScreen,
            )
        }
        AnimatedVisibility(visible = !isRegister) {
            LoginContent(
                modifier = Modifier.padding(innerPadding),
                goToRegister = {
                    isRegister = true
                },
                executeLogin = { name, password ->
                    viewModel.onEvent(AuthEvent.BeginLogin(name, password))
                },
                loginState = loginState,
                revertBackToNormalState = {
                    viewModel.onEvent(AuthEvent.DismissDialog)
                },
                goToHomeScreen = goToHomeScreen
            )
        }
    }
}