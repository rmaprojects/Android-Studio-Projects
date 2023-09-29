package com.rmaprojects.jetnotes.ui.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotezeezTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigationIconClick: () -> Unit = {},
    actionButtons: List<TopBarAction>? = null,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = {
            if (title != "Notezeez")
                IconButton(onClick = onNavigationIconClick) {
                    Icon(
                        Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back"
                    )
                }
            else
                IconButton(
                    onClick = onNavigationIconClick,
                ) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings")
                }
        },
        actions = {
            if (!actionButtons.isNullOrEmpty())
                LazyRow(
                    userScrollEnabled = false
                ) {
                    items(actionButtons) { actions ->
                        if (actions.icon != null)
                            IconButton(onClick = actions.onClick) {
                                Icon(actions.icon, contentDescription = actions.title)
                            }
                        else
                            TextButton(onClick = actions.onClick ) {
                                Text(text = actions.title)
                            }
                    }
                }
        }
    )
}


@Preview
@Composable
fun JetNoteTopBarPreview() {
    NotezeezTopBar(
        title = "Dashboard",
        onNavigationIconClick = {},
        actionButtons = listOf(
            TopBarAction(
                "Settings",
                Icons.Default.Settings,
            ) {},
            TopBarAction(
                "Search",
                Icons.Default.Search,
            ) {},
        ),
    )
}

data class TopBarAction(
    val title: String,
    val icon: ImageVector? = null,
    val onClick: () -> Unit,
)