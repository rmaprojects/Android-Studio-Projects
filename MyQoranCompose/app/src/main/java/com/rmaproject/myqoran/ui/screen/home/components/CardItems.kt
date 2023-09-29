package com.rmaproject.myqoran.ui.screen.home.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rmaproject.myqoran.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurahCardItem(
    ayahNumber: Int,
    surahName: String,
    surahNameId: String,
    surahNameAr: String,
    navigateToReadQoran: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
            .padding(4.dp),
        onClick = navigateToReadQoran
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .width(32.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.Circle,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "$ayahNumber",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
            Column(
                modifier = Modifier
                    .weight(4f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp),
            ) {
                Text(
                    text = surahName,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = surahNameId,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                modifier = Modifier.weight(1f),
                text = surahNameAr,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = FontFamily(Font(R.font.usmani_font))
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuzCardItem(
    juzNumber: Int,
    surahList: List<String?>?,
    surahNumberList: List<Int?>?,
    ayahOfSurahList: List<Int?>?,
    navigateToReadQoran: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isSurahListShowed by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        onClick = navigateToReadQoran
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(32.dp)
                            .weight(1F)
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize()
                                .size(48.dp),
                            imageVector = Icons.Default.Circle,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            text = "$juzNumber",
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(3F)
                    ) {
                        Text(
                            text = stringResource(R.string.txt_juz, juzNumber),
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {
                        isSurahListShowed = !isSurahListShowed
                    }
                ) {
                    Icon(
                        imageVector = if (!isSurahListShowed) Icons.Default.KeyboardArrowDown
                        else Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                    )
                }
            }
            AnimatedVisibility(
                visible = isSurahListShowed,
            ) {
                if (!surahList.isNullOrEmpty() && !ayahOfSurahList.isNullOrEmpty() && !surahNumberList.isNullOrEmpty()) {
                    for (index in surahList.indices) {
                        Card(
                            modifier = Modifier.padding(4.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(32.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier.fillMaxSize(),
                                        imageVector = Icons.Default.Circle,
                                        tint = MaterialTheme.colorScheme.primary,
                                        contentDescription = null
                                    )
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.Center),
                                        text = "${surahNumberList[index]}",
                                        color = MaterialTheme.colorScheme.onPrimary,
                                    )
                                }
                                Text(
                                    text = "${surahList[index]}: ${ayahOfSurahList[index]}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageCardItem(
    pageNumber: Int,
    navigateToReadQoran: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
            .padding(4.dp),
        onClick = navigateToReadQoran
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .width(32.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.Circle,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "$pageNumber",
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
            Column(
                modifier = Modifier
                    .weight(5f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp),
            ) {
                Text(
                    text = stringResource(R.string.txt_page, pageNumber),
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}