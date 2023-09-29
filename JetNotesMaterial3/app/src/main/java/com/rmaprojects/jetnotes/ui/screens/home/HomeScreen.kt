package com.rmaprojects.jetnotes.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmaprojects.jetnotes.data.local.database.model.Note
import com.rmaprojects.jetnotes.ui.components.NotezeezCardItem
import com.rmaprojects.jetnotes.ui.components.NotezeezTopBar
import com.rmaprojects.jetnotes.ui.components.NotezeezDialog
import com.rmaprojects.jetnotes.ui.components.TopBarAction
import com.rmaprojects.jetnotes.ui.screens.home.state.NoteState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    goToAddNote: () -> Unit,
    goToEditNote: (Int) -> Unit,
    goToSettings: () -> Unit,
    goToSearch: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    var isDialogShown by remember {
        mutableStateOf(false)
    }

    var deleteQueue: Note? = null

    Scaffold(
        topBar = {
            NotezeezTopBar(
                title = "Notezeez",
                actionButtons = listOf(
                    TopBarAction(
                        title = "Search",
                        icon = Icons.Default.Search,
                        onClick = goToSearch
                    )
                ),
                onNavigationIconClick = goToSettings
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Add Notes") },
                icon = { Icon(Icons.Default.Add, contentDescription = "Add Notes") },
                onClick = goToAddNote
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        viewModel.noteState.collectAsState(initial = NoteState.Empty).let { noteState ->
            val state = noteState.value
            HomeContent(
                state = state,
                modifier = Modifier.padding(innerPadding),
                goToEditNote = goToEditNote,
                deleteNote = { note ->
                    deleteQueue = note
                    isDialogShown = true
                },
            )
        }
        if (isDialogShown) {
            NotezeezDialog(
                title = "Yakin ingin menghapus?",
                message = "Aksi ini tidak dapat dipulihkan",
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteNote(deleteQueue!!)
                        deleteQueue = null
                        isDialogShown = false
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
                onDismiss = {
                    isDialogShown = false
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    state: NoteState<List<Note>>,
    goToEditNote: (Int) -> Unit,
    deleteNote: (Note) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        is NoteState.Empty -> {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "There's no note here, try to add some.",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
        }

        is NoteState.NotEmpty -> {
            val noteList = state.data
            LazyVerticalStaggeredGrid(
                modifier = modifier.padding(8.dp),
                columns = StaggeredGridCells.Adaptive(128.dp),
                contentPadding = PaddingValues(horizontal = 2.dp),
            ) {
                items(
                    items = noteList,
                    key = { it.id!! }
                ) { note ->
                    NotezeezCardItem(
                        modifier = Modifier.padding(2.dp),
                        noteTitle = note.title ?: "Untitled",
                        noteContent = note.content ?: "",
                        goToEditNote = { goToEditNote(note.id!!) },
                        deleteNote = { deleteNote(note) }
                    )
                }
            }
        }
    }
}