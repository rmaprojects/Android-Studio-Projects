package com.rmaprojects.studentmonitoring.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyAlertDialog(
    title: String,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    confirmText: String,
    message: String? = ""
) {

    val scrollState = rememberScrollState()

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = confirmText)
            }
        },
        title = {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                Text(title)
            }
        },
        text = {
            Text("$message")
        }
    )
}

@Composable
fun MyAlertDialog(
    title: String,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    confirmText: String,
    dismissText: String,
    message: String? = ""
) {

    val scrollState = rememberScrollState()

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = confirmText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = dismissText, color = MaterialTheme.colorScheme.error)
            }
        },
        title = {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                Text(title)
            }
        },
        text = {
            Text("$message")
        }
    )
}