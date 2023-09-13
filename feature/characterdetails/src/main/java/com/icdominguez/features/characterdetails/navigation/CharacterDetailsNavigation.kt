package com.icdominguez.features.characterdetails.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.icdominguez.features.characterdetails.CharacterDetailsScreenViewModel
import com.icdominguez.features.characterdetails.composables.CharacterDetailsScreen

const val characterDetailsRoute = "character_details_route"
const val characterIdArg = "characterId"

fun NavController.navigateToCharacterDetails(characterId: Int) {
    this.navigate("$characterDetailsRoute/$characterId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.characterDetailsScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "$characterDetailsRoute/{$characterIdArg}",
        arguments = listOf(
            navArgument(characterIdArg) {
                type = NavType.IntType
            },
        ),
    ) { navBackStackEntry ->
        val viewModel = hiltViewModel<CharacterDetailsScreenViewModel>(navBackStackEntry)
        CharacterDetailsScreen(
            viewModel = viewModel,
            uiEvent = viewModel::uiEvent,
            onBackClick = onBackClick
        )
    }
}
