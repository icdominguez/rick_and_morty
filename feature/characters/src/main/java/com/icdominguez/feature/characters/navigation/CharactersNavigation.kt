package com.icdominguez.feature.characters.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.icdominguez.feature.characters.CharactersScreenViewModel
import com.icdominguez.feature.characters.composables.CharactersScreen

const val charactersRoute = "characters_route"

fun NavController.navigateToCharacters(navOptions: NavOptions? = null) {
    this.navigate(charactersRoute, navOptions)
}

fun NavGraphBuilder.charactersScreen(
    onCharacterClick: (Int) -> Unit,
) {
    composable(route = charactersRoute) { navBackStackEntry ->
        val viewModel = hiltViewModel<CharactersScreenViewModel>(navBackStackEntry)
        CharactersScreen(
            viewModel = viewModel,
            uiEvent = viewModel::uiEvent,
            onCharacterClick = onCharacterClick,
        )
    }
}
