package com.rmaprojects.studentmonitoring.presentation.ui.pages.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmaprojects.studentmonitoring.presentation.ui.components.HistoryCard
import com.rmaprojects.studentmonitoring.presentation.ui.pages.history.state.HistoryScreenState

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {

    val historyState by viewModel.historyState.collectAsState()

    when (val state = historyState) {
        is HistoryScreenState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.message,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        is HistoryScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is HistoryScreenState.Success -> {
            LazyColumn {
                items(state.historyList) { data ->
                    HistoryCard(
                        studentName = data.studentName,
                        description = data.description,
                        teacherName = data.teacherName,
                        point = data.point
                    )
                }
            }
        }
    }
}