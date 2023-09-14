package com.icdominguez.feature.characters.composables.gridview

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.icdominguez.core.model.Character

@Composable
fun CharactersGridView(
    items: LazyPagingItems<Character>,
    gridState: LazyGridState = rememberLazyGridState(),
    onCharacterClicked: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState,
    ) {
        items(items.itemCount) { index ->
            items[index]?.let { character ->
                CharacterGridItem(
                    character = character,
                    onCharacterClicked = onCharacterClicked,
                )
            }
        }
    }
}
