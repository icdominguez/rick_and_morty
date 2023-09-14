package com.icdominguez.feature.characters.composables.listview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.icdominguez.core.model.Character

@Composable
fun CharactersListView(
    items: LazyPagingItems<Character>,
    listState: LazyListState = rememberLazyListState(),
    onCharacterClicked: (Int) -> Unit,
) {
    LazyColumn(
        state = listState,
    ) {
        items(items.itemCount) { index ->
            CharacterCard(
                character = items[index]!!,
                onCharacterClicked = { onCharacterClicked(it) },
            )
        }
    }
}
