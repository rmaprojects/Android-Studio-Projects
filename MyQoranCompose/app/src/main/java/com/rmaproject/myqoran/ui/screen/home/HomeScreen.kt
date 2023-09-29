package com.rmaproject.myqoran.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmaproject.myqoran.R
import com.rmaproject.myqoran.components.FastScrollItem
import com.rmaproject.myqoran.components.MyQoranHomeAppBar
import com.rmaproject.myqoran.data.kotpref.LastReadPreferences
import com.rmaproject.myqoran.ui.navigation.MyQoranSharedViewModel
import com.rmaproject.myqoran.ui.screen.home.components.*
import kotlinx.coroutines.launch
import my.nanihadesuka.compose.LazyColumnScrollbar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigateToReadQoran: (Int, Int?, Int?, Int?) -> Unit,
    navigateLastRead: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToBookmark: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    sharedViewModel: MyQoranSharedViewModel,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { tabItems.size })

    val surahScrollState = rememberLazyListState()
    val juzScrollState = rememberLazyListState()
    val pageScrollState = rememberLazyListState()

    val surahState = viewModel.surahState.value
    val juzState = viewModel.juzState.value
    val pageState = viewModel.pageState.value

    LaunchedEffect(surahState) {
        surahState.surahList?.let { sharedViewModel.setTotalAyah(it) }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            MyQoranHomeAppBar(
                goToSearch = navigateToSearch,
                openDrawer = openDrawer,
                currentDestination = null
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f)
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    HeaderCard(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        cardName = stringResource(R.string.txt_last_read),
                        icon = Icons.Default.History,
                        cardDescription =
                        if (LastReadPreferences.surahName == null) stringResource(R.string.txt_desc_no_last_read)
                        else stringResource(
                            R.string.txt_desc_continue_read,
                            "\n${LastReadPreferences.surahName}: ${LastReadPreferences.ayahNumber}"
                        ),
                        onClick = navigateLastRead
                    )
                    HeaderCard(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        cardName = "Bookmark",
                        icon = Icons.Default.Bookmark,
                        cardDescription = stringResource(R.string.desc_bookmark_card),
                        onClick = navigateToBookmark
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(4f)
            ) {
                TabRow(
                    modifier = Modifier.height(48.dp),
                    selectedTabIndex = pagerState.currentPage,
                    indicator = { tabPositions ->
                        tabPositions.forEach {
                            TabRowDefaults.Indicator(
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                ) {
                    tabItems.forEachIndexed { index, tabItem ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = MaterialTheme.colorScheme.onSurface
                        ) {
                            Text(text = tabItem.title)
                        }
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize(),
                ) { currentPage ->
                    val currentPageOffset = remember { derivedStateOf { pagerState.currentPageOffsetFraction } }
                    when (currentPage) {
                        ORDER_BY_SURAH -> {
                            LazyColumnScrollbar(
                                listState = surahScrollState,
                                thumbColor = MaterialTheme.colorScheme.primary,
                                indicatorContent = { position, _ ->
                                    if (!surahState.surahList.isNullOrEmpty())
                                        FastScrollItem(
                                            text = surahState.surahList[position].surahNameEN ?: ""
                                        )
                                },
                            ) {
                                LazyColumn(
                                    state = surahScrollState,
                                    modifier = Modifier.offset(currentPageOffset.value.dp),
                                ) {
                                    items(
                                        surahState.surahList ?: emptyList(),
                                        key = { it.id!! }
                                    ) { surah ->
                                        SurahCardItem(
                                            ayahNumber = surah.surahNumber!!,
                                            surahName = surah.surahNameEN!!,
                                            surahNameId = surah.surahNameID!!,
                                            surahNameAr = surah.surahNameArabic!!,
                                            navigateToReadQoran = {
                                                navigateToReadQoran(
                                                    ORDER_BY_SURAH,
                                                    surah.surahNumber, null, null
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }
                        ORDER_BY_JUZ -> {
                            LazyColumnScrollbar(
                                listState = juzScrollState,
                                thumbColor = MaterialTheme.colorScheme.primary,
                                indicatorContent = { position, _ ->
                                    if (!juzState.juzList.isNullOrEmpty())
                                        FastScrollItem(
                                            text = stringResource(
                                                id = R.string.txt_juz,
                                                juzState.juzList[position].juzNumber ?: 1
                                            )
                                        )
                                }
                            ) {
                                LazyColumn(
                                    state = juzScrollState,
                                    modifier = Modifier.offset(currentPageOffset.value.dp)
                                ) {
                                    val juzList = juzState.juzList?.distinctBy { it.juzNumber }
                                    items(juzList ?: emptyList()) { juz ->
                                        val filteredSurahList = juzState.juzList?.filter { it.juzNumber == juz.juzNumber }
                                        JuzCardItem(
                                            juzNumber = juz.juzNumber!!,
                                            navigateToReadQoran = {
                                                navigateToReadQoran(
                                                    ORDER_BY_JUZ,
                                                    null,
                                                    juz.juzNumber,
                                                    null
                                                )
                                            },
                                            surahList = filteredSurahList?.map { it.SurahName_en },
                                            surahNumberList = filteredSurahList?.map { it.surahNumber },
                                            ayahOfSurahList = filteredSurahList?.map { it.nomorAyah }
                                        )
                                    }
                                }
                            }
                        }
                        ORDER_BY_PAGE -> {
                            LazyColumnScrollbar(
                                listState = pageScrollState,
                                thumbColor = MaterialTheme.colorScheme.primary,
                                indicatorContent = { position, _ ->
                                    if (!pageState.qoranByPageList.isNullOrEmpty())
                                        FastScrollItem(
                                            text = stringResource(
                                                id = R.string.txt_page,
                                                pageState.qoranByPageList[position].pageNumber ?: 1
                                            )
                                        )
                                }
                            ) {
                                LazyColumn(
                                    state = pageScrollState,
                                    modifier = Modifier.offset(currentPageOffset.value.dp)
                                ) {
                                    items(pageState.qoranByPageList ?: emptyList()) { qoranByPage ->
                                        PageCardItem(
                                            pageNumber = qoranByPage.pageNumber!!,
                                            navigateToReadQoran = {
                                                navigateToReadQoran(
                                                    ORDER_BY_PAGE,
                                                    null,
                                                    null,
                                                    qoranByPage.pageNumber
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

const val ORDER_BY_SURAH = 0
const val ORDER_BY_JUZ = 1
const val ORDER_BY_PAGE = 2