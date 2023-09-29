package com.rmaprojects.jetnotes.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.rmaprojects.jetnotes.data.local.FontSize
import com.rmaprojects.jetnotes.data.local.SharedPreference
import com.rmaprojects.jetnotes.ui.theme.JetNotesTheme
import com.rmaprojects.jetnotes.ui.theme.currentFontSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotezeezCardItem(
    noteTitle: String,
    noteContent: String,
    goToEditNote: () -> Unit,
    deleteNote: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        onClick = goToEditNote,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(5f),
                    text = noteTitle,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    onClick = deleteNote,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete Note"
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = noteContent,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoteCardItemsPreview() {
    JetNotesTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            NotezeezCardItem(
                noteTitle = "Hello World",
                noteContent = "Halo, ini adalah teks untuk mengetes card ini.",
                {},
                {}
            )
        }
    }
}

@Composable
fun ThemeChangeCard(
    switchStatus: Boolean,
    onSwitchChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .height(128.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .weight(1F),
                imageVector = Icons.Default.DarkMode,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2F)
            ) {
                Text(
                    text = "Dark Mode",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ubah tema aplikasi",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Switch(
                modifier = Modifier.weight(1F),
                checked = switchStatus,
                onCheckedChange = {
                    onSwitchChanged(it)
                }
            )
        }
    }
}

@Composable
fun FontSizeChangerCard(
    isMenuExpanded: Boolean,
    onMenuClicked: () -> Unit,
    onMenuDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .height(128.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .weight(1F),
                imageVector = Icons.Default.TextFormat,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2F)
            ) {
                Text(
                    text = "Text Size",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Change note font size",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Box(
                modifier = Modifier.padding(horizontal = 6.dp)
            ) {
                TextButton(
                    onClick = {
                        onMenuClicked()
                    },
                ) {
                    Text(text = currentFontSize.value.text)
                }
                DropdownMenu(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    expanded = isMenuExpanded,
                    onDismissRequest = { onMenuDismiss() },
                    offset = DpOffset(y = 2.dp, x = 0.dp)
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Small")
                        },
                        onClick = {
                            SharedPreference.fontSize = FontSize.SMALL
                            currentFontSize.value = FontSize.SMALL
                            onMenuDismiss()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Default")
                        },
                        onClick = {
                            SharedPreference.fontSize = FontSize.DEFAULT
                            currentFontSize.value = FontSize.DEFAULT
                            onMenuDismiss()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Large")
                        },
                        onClick = {
                            SharedPreference.fontSize = FontSize.LARGE
                            currentFontSize.value = FontSize.LARGE
                            onMenuDismiss()
                        }
                    )
                }
            }
        }
    }
}