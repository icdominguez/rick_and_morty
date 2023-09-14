package com.icdominguez.feature.characters.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SettingsInputComponent
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.icdominguez.core.model.Character
import com.icdominguez.feature.characters.CharactersScreenViewModel
import com.icdominguez.feature.characters.composables.gridview.CharactersGridView
import com.icdominguez.feature.characters.composables.listview.CharactersListView
import com.icdominguez.rickandmorty.core.designsystem.component.FullScreenLoading
import com.icdominguez.rickandmorty.core.designsystem.component.NoDataFound
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    viewModel: CharactersScreenViewModel,
    uiEvent: (CharactersScreenViewModel.Event) -> Unit,
    onCharacterClick: (Int) -> Unit,
) {
    val state = viewModel.state.collectAsState().value

    val lazyListState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Characters")
                },
                actions = {
                    IconButton(
                        onClick = { uiEvent(CharactersScreenViewModel.Event.OnChangeViewButtonClicked) },
                    ) {
                        Icon(
                            imageVector = if (state.showColumn) Icons.Default.GridView else Icons.Default.List,
                            contentDescription = "",
                        )
                    }
                    IconButton(onClick = { uiEvent(CharactersScreenViewModel.Event.OnFilterButtonClicked) }) {
                        Icon(
                            imageVector = Icons.Default.SettingsInputComponent,
                            contentDescription = "Button filter",
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    DockedSearchBar(
                        modifier = Modifier.fillMaxWidth(),
                        query = state.searchText,
                        onQueryChange = {
                            uiEvent(
                                CharactersScreenViewModel.Event.OnSearchTextChanged(
                                    it,
                                ),
                            )
                        },
                        onSearch = { uiEvent(CharactersScreenViewModel.Event.OnSearchClicked) },
                        active = state.isSearchBarActive,
                        onActiveChange = { uiEvent(CharactersScreenViewModel.Event.OnSearchBarActiveChange) },
                        placeholder = {
                            Text(text = "Search")
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "")
                        },
                        trailingIcon = {
                            if (state.isSearchBarActive) {
                                Icon(
                                    modifier = Modifier.clickable {
                                        uiEvent(
                                            CharactersScreenViewModel.Event.OnSearchBarClose,
                                        )
                                    },
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "",
                                )
                            }
                        },
                    ) {
                        LazyColumn {
                            items(state.searchHistory) {
                                Row(
                                    modifier = Modifier
                                        .padding(all = 14.dp)
                                        .clickable {
                                            uiEvent(
                                                CharactersScreenViewModel.Event.OnHistoryItemClicked(
                                                    it,
                                                ),
                                            )
                                        },
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(end = 10.dp),
                                        imageVector = Icons.Default.History,
                                        contentDescription = "History Icon",
                                    )
                                    Text(text = it)
                                }
                            }
                        }
                    }
                }

                val characterPagingItems: LazyPagingItems<Character> =
                    viewModel.charactersState.collectAsLazyPagingItems()

                when (characterPagingItems.loadState.refresh) {
                    is LoadState.Loading -> {
                        FullScreenLoading()
                    }

                    is LoadState.Error -> {
                        NoDataFound()
                    }

                    else -> {
                        if (state.showColumn) {
                            CharactersListView(
                                items = characterPagingItems,
                                listState = lazyListState,
                                onCharacterClicked = { onCharacterClick(it) },
                            )
                        } else {
                            CharactersGridView(
                                items = characterPagingItems,
                                gridState = lazyGridState,
                                onCharacterClicked = { onCharacterClick(it) },
                            )
                        }
                    }
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(10.dp),
                onClick = {
                    coroutineScope.launch {
                        if (state.showColumn) {
                            lazyListState.animateScrollToItem(0)
                        } else {
                            lazyGridState.animateScrollToItem(0)
                        }
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Floating action button",
                )
            }

            if (state.showBottomSheetDialog) {
                FilterBottomSheet(
                    bottomSheetState = modalBottomSheetState,
                    onDismissRequest = { uiEvent(CharactersScreenViewModel.Event.OnBottomSheetDialogDismissed) },
                    onApplyClicked = { uiEvent(CharactersScreenViewModel.Event.OnBottomSheetDialogAccepted) },
                    filterStatusValues = state.listFilterStatus,
                    filterGenderValues = state.listFilterGender,
                    onFilterStatusSelected = {
                        uiEvent(
                            CharactersScreenViewModel.Event.OnFilterStatusSelected(
                                it,
                            ),
                        )
                    },
                    onFilterGenderSelected = {
                        uiEvent(
                            CharactersScreenViewModel.Event.OnFilterGenderSelected(
                                it,
                            ),
                        )
                    },
                    onResetFilterButtonClicked = { uiEvent(CharactersScreenViewModel.Event.OnResetFilterButtonClicked) },
                )
            }
        }
    }
}

@Composable
@Preview
fun CharactersScreenPreview() {
}
