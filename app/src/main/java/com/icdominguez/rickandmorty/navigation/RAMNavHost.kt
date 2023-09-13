package com.icdominguez.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.icdominguez.feature.characters.navigation.charactersRoute
import com.icdominguez.feature.characters.navigation.charactersScreen
import com.icdominguez.feature.episodes.navigation.episodesScreen
import com.icdominguez.feature.locations.navigation.locationsScreen
import com.icdominguez.features.characterdetails.navigation.characterDetailsScreen
import com.icdominguez.features.characterdetails.navigation.navigateToCharacterDetails
import com.icdominguez.rickandmorty.ui.RAMAppState

@Composable
fun RAMNavHost(
    appState: RAMAppState,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = charactersRoute,
    ) {
        charactersScreen(
            onCharacterClick = navController::navigateToCharacterDetails,
        )
        characterDetailsScreen(
            onBackClick = navController::popBackStack,
        )
        locationsScreen()
        episodesScreen()
    }
}
