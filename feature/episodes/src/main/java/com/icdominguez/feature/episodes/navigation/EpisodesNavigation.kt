package com.icdominguez.feature.episodes.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.icdominguez.feature.episodes.EpisodesScreen

const val episodesRoute = "episodes_route"

fun NavController.navigateToEpisodes(navOptions: NavOptions? = null) {
    this.navigate(episodesRoute, navOptions)
}

fun NavGraphBuilder.episodesScreen() {
    composable(route = episodesRoute) {
        EpisodesScreen()
    }
}
