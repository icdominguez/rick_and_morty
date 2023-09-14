package com.icdominguez.rickandmorty.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.icdominguez.feature.characters.navigation.charactersRoute
import com.icdominguez.feature.characters.navigation.navigateToCharacters
import com.icdominguez.feature.episodes.navigation.episodesRoute
import com.icdominguez.feature.episodes.navigation.navigateToEpisodes
import com.icdominguez.feature.locations.navigation.locationsRoute
import com.icdominguez.feature.locations.navigation.navigateToLocations
import com.icdominguez.rickandmorty.navigation.TopLevelDestination
import com.icdominguez.rickandmorty.navigation.TopLevelDestination.CHARACTERS
import com.icdominguez.rickandmorty.navigation.TopLevelDestination.EPISODES
import com.icdominguez.rickandmorty.navigation.TopLevelDestination.LOCATIONS

@Composable
fun rememberRAMAppState(
    navController: NavHostController = rememberNavController(),
): RAMAppState {
    return remember(
        navController,
    ) {
        RAMAppState(navController = navController)
    }
}

@Stable
class RAMAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            charactersRoute -> CHARACTERS
            episodesRoute -> EPISODES
            locationsRoute -> LOCATIONS
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            CHARACTERS -> navController.navigateToCharacters(topLevelNavOptions)
            EPISODES -> navController.navigateToEpisodes(topLevelNavOptions)
            LOCATIONS -> navController.navigateToLocations(topLevelNavOptions)
        }
    }
}
