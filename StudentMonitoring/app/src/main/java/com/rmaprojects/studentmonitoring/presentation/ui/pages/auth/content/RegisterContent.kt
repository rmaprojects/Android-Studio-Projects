package com.rmaprojects.studentmonitoring.presentation.ui.pages.auth.content

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rmaprojects.studentmonitoring.presentation.ui.components.MyAlertDialog
import com.rmaprojects.studentmonitoring.presentation.ui.state.LogicState

@Composable
fun RegisterContent(
    goToLogin: () -> Unit,
    executeRegister: (name: String, email: String, password: String) -> Unit,
    registerState: LogicState?,
    modifier: Modifier = Modifier,
    revertBackToNormalState: () -> Unit,
    goToHomeScreen: () -> Unit
) {

    var nameValue by remember {
        mutableStateOf("")
    }
    var emailValue by remember {
        mutableStateOf("")
    }
    var passwordValue by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(128.dp))
        Text(
            text = "Register",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(128.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nameValue,
            onValueChange = { newValue ->
                nameValue = newValue
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            },
            singleLine = true,
            label = {
                Text("Nama Anda")
            },
            isError = nameValue.isEmpty(),
            supportingText = {
                if (nameValue.isEmpty()) {
                    Text(text = "Nama tidak boleh kosong")
                }
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailValue,
            onValueChange = { newValue ->
                emailValue = newValue
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null)
            },
            singleLine = true,
            label = {
                Text("Email")
            },
            isError = (!Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()),
            supportingText = {
                if (!Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
                    Text(text = "Format Email tidak didukung")
                }
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordValue,
            onValueChange = { newValue ->
                if (passwordValue.length <= 8) passwordValue = newValue
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null)
            },
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.VisibilityOff
                        else Icons.Default.Visibility,
                        contentDescription = "Show Password"
                    )
                }
            },
            singleLine = true,
            label = {
                Text("Password")
            },
            visualTransformation = if (showPassword) VisualTransformation.None
            else PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            onClick = {
                executeRegister(nameValue, emailValue, passwordValue)
            },
            enabled = registerState !is LogicState.Loading
        ) {
            Text(text = "Register")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = { goToLogin() },
            enabled = registerState !is LogicState.Loading
        ) {
            Text(text = "Have an account? Log in")
        }


        when (registerState) {
            is LogicState.Error -> {
                MyAlertDialog(
                    title = registerState.message,
                    onDismiss = revertBackToNormalState,
                    onConfirm = revertBackToNormalState,
                    confirmText = "Ok"
                )
            }
            is LogicState.Loading -> {
                Spacer(modifier = Modifier.height(24.dp))
                CircularProgressIndicator()
            }
            is LogicState.Success -> {
                goToHomeScreen()
            }
            null -> {}
        }
    }
}

@Preview
@Composable
fun RegisterContentPreview() {
    RegisterContent(goToLogin = { }, executeRegister = { _, _, _ -> }, null, revertBackToNormalState = {

    }, goToHomeScreen = {  })
}