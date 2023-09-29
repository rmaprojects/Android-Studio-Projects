package com.rmaprojects.jetnotes.ui.screens.searchnote

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmaprojects.jetnotes.data.local.database.model.Note
import com.rmaprojects.jetnotes.ui.components.NotezeezCardItem
import com.rmaprojects.jetnotes.ui.components.NotezeezSearchField
import com.rmaprojects.jetnotes.ui.components.NotezeezTopBar
import com.rmaprojects.jetnotes.ui.screens.searchnote.event.SearchNoteEvent
import com.rmaprojects.jetnotes.ui.screens.searchnote.event.SearchNoteUiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchNoteScreen(
    navigateUp: () -> Unit,
    goToEditNote: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchNoteViewModel = hiltViewModel()
) {

    val searchFieldState = viewModel.searchQuery.value
    val noteList = viewModel.searchListState.value

    LaunchedEffect(key1 = searchFieldState.text) {
        viewModel.onEvent(SearchNoteEvent.FindNote)
    }

    Scaffold(
        topBar = {
            NotezeezTopBar(
                title = "Search Notes",
                onNavigationIconClick = navigateUp,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            NotezeezSearchField(
                query = searchFieldState.text,
                onValueChange = { newValue ->
                    viewModel.onEvent(SearchNoteEvent.EnteredQuery(newValue))
                },
                onFocusState = { newValue ->
                    viewModel.onEvent(SearchNoteEvent.QueryChangeFocus(newValue))
                },
                displayHint = searchFieldState.isHintVisible,
            )
            Spacer(modifier = Modifier.height(12.dp))
            viewModel.searchNoteUiEventFlow.collectAsState(initial = SearchNoteUiEvent.EmptyQuery).let { state ->
                val event = state.value
                SearchNoteContent(
                    event = event,
                    currentQuery = searchFieldState.text,
                    noteList = noteList,
                    deleteNote = { viewModel.onEvent(SearchNoteEvent.DeleteNote(it)) },
                    goToEditNote = goToEditNote
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchNoteContent(
    event: SearchNoteUiEvent,
    currentQuery: String,
    deleteNote: (Note) -> Unit,
    goToEditNote: (Int) -> Unit,
    modifier: Modifier = Modifier,
    noteList: List<Note> = emptyList(),
) {
    when (event) {
        is SearchNoteUiEvent.EmptyQuery -> {
            EmptyScreen(
                message = "Please fill the search box above",
                modifier = modifier
            )
        }
        is SearchNoteUiEvent.Error -> {
            EmptyScreen(
                message = event.message,
                modifier = modifier
            )
        }
        is SearchNoteUiEvent.NotFoundNote -> {
            EmptyScreen(
                message = "There's no such note with: \"$currentQuery\"",
                modifier = modifier
            )
        }
        is SearchNoteUiEvent.FoundNote -> {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(128.dp),
                modifier = modifier
                    .padding(12.dp),
            ) {
                items(noteList) { note ->
                    NotezeezCardItem(
                        modifier = Modifier.padding(4.dp),
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

@Composable
fun EmptyScreen(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(12.dp),
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
fun EmptyScreenPreview() {
    EmptyScreen(
        message = "Hello World"
    )
}