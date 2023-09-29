package com.rmaprojects.jetnotes.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NotezeezDialog(
    title: String,
    message: String,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            confirmButton()
        },
        dismissButton = {
            dismissButton()
        }
    )
}