package com.rmaprojects.jetnotes.ui.screens.insertnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmaprojects.jetnotes.ui.components.NotezeezDialog
import com.rmaprojects.jetnotes.ui.components.NotezeezTextField
import com.rmaprojects.jetnotes.ui.components.NotezeezTopBar
import com.rmaprojects.jetnotes.ui.components.TopBarAction
import com.rmaprojects.jetnotes.ui.screens.insertnote.event.InsertNoteEvent
import com.rmaprojects.jetnotes.ui.screens.insertnote.state.DeleteNoteUiEvent
import com.rmaprojects.jetnotes.ui.screens.insertnote.state.InsertNoteUiEvent
import com.rmaprojects.jetnotes.ui.theme.currentFontSize
import kotlinx.coroutines.flow.collectLatest

@Composable
fun InsertNoteScreen(
    navigateToDashboard: () -> Unit,
    navigateUp: () -> Unit,
    viewModel: InsertEditNoteViewModel = hiltViewModel(),
) {

    val snackbarHostState = SnackbarHostState()
    val isEdit = viewModel.noteId != -1

    LaunchedEffect(key1 = true) {
        viewModel.insertNoteUiEvent.collectLatest { event ->
            when (event) {
                is InsertNoteUiEvent.SaveNote -> {
                    if (isEdit) return@collectLatest
                    navigateToDashboard()
                }

                is InsertNoteUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.deleteNoteUiEvent.collectLatest { event ->
            when (event) {
                is DeleteNoteUiEvent.DeleteNote -> {
                    navigateToDashboard()
                }

                is DeleteNoteUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    InsertNoteContent(
        viewModel = viewModel,
        snackbarHostState = snackbarHostState,
        navigateUp = navigateUp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertNoteContent(
    viewModel: InsertEditNoteViewModel,
    snackbarHostState: SnackbarHostState,
    navigateUp: () -> Unit,
) {

    val noteTitle = viewModel.noteTitle.value
    val noteContent = viewModel.noteContent.value
    val isEdit = viewModel.noteId != -1

    var isDialogShown by remember {
        mutableStateOf(false)
    }

    val topBarActionList = mutableListOf(
        TopBarAction(
            title = "Save",
            onClick = { viewModel.onEvent(InsertNoteEvent.SaveNote) },
            icon = Icons.Default.Save
        ),
    )

    if (isEdit) {
        topBarActionList.removeAt(0)
        topBarActionList.add(
            TopBarAction(
                title = "Delete",
                onClick = {
                    isDialogShown = true
                },
                icon = Icons.Default.Delete
            )
        )
    }

    Scaffold(
        topBar = {
            NotezeezTopBar(
                title = if (isEdit) "Edit Note" else "New Note",
                onNavigationIconClick = navigateUp,
                actionButtons = topBarActionList
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                NotezeezTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    text = noteTitle.text,
                    hint = noteTitle.hint,
                    onValueChange = {
                        viewModel.onEvent(InsertNoteEvent.EnteredTitle(it))
                        if (isEdit) viewModel.onEvent(InsertNoteEvent.SaveNote)
                    },
                    onFocusState = {
                        viewModel.onEvent(InsertNoteEvent.ChangeTitleFocus(it))
                    },
                    isHintVisible = noteTitle.isHintVisible,
                    singleLine = true,
                    textStyle = currentFontSize.value.titleStyle
                )
            }
            Spacer(modifier = Modifier.height(36.dp))
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                NotezeezTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    text = noteContent.text,
                    hint = noteContent.hint,
                    onValueChange = {
                        viewModel.onEvent(InsertNoteEvent.EnteredContent(it))
                        if (isEdit) viewModel.onEvent(InsertNoteEvent.SaveNote)
                    },
                    onFocusState = {
                        viewModel.onEvent(InsertNoteEvent.ChangeContentFocus(it))
                    },
                    isHintVisible = noteContent.isHintVisible,
                    textStyle = currentFontSize.value.contentStyle,
                    singleLine = false,
                )
            }
        }
        if (isDialogShown) {
            NotezeezDialog(
                title = "Delete this note?",
                message = "This action cannot be undone",
                onDismiss = {
                    isDialogShown = false
                },
                confirmButton = {
                    TextButton(onClick = {
                        isDialogShown = false
                        viewModel.onEvent(InsertNoteEvent.DeleteNote)
                    }) {
                        Text(text = "Ya")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        isDialogShown = false
                    }) {
                        Text(text = "Tidak")
                    }
                },
            )
        }
    }
}