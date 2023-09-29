package com.rmaprojects.jetnotes.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rmaprojects.jetnotes.data.local.SharedPreference
import com.rmaprojects.jetnotes.ui.components.FontSizeChangerCard
import com.rmaprojects.jetnotes.ui.components.NotezeezTopBar
import com.rmaprojects.jetnotes.ui.components.ThemeChangeCard
import com.rmaprojects.jetnotes.ui.theme.isDarkMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    var isMenuExpanded by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            NotezeezTopBar(
                title = "Settings",
                onNavigationIconClick = navigateUp
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            ThemeChangeCard(
                modifier = Modifier.padding(12.dp),
                switchStatus = isDarkMode.value,
                onSwitchChanged = {
                    isDarkMode.value = it
                    SharedPreference.isDarkMode = it
                }
            )
            FontSizeChangerCard(
                modifier = Modifier.padding(12.dp),
                isMenuExpanded = isMenuExpanded,
                onMenuClicked = {
                    isMenuExpanded = true
                },
                onMenuDismiss = {
                    isMenuExpanded = false
                }
            )
        }
    }
}